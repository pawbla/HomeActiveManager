package com.pawbla.project.home.embedded.sensor.reader;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class DHTReader implements Reader {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private static final int DHT_MAX_COUNT = 120;
    private static final int DHT_PULSES = 41;
    private static final int DHT_PULSES_DOUBLED = DHT_PULSES * 2;

    private final int pin;
    private final int supplyPin;
    private int controlVal;
    private int temperature;
    private int humidity;
    private boolean isError;

    public DHTReader(@Value("${custom.dhtDataPin}") int pin, @Value("${custom.dhtSupplyPin}") int supplyPin) {
        controlVal = 0;
        this.pin = pin;
        this.supplyPin = supplyPin;
        this.isError = true;
    }

    @Override
    public void read() {
        logger.info("Read data from DHT sensor");
        controlVal++;
        isError = true;
        //TODO Maybe it could be handle in better way
        if (controlVal > 5) {
            logger.warn("Control values has exceeded: " + controlVal);
        }
        int[] pulseCnt = new int [DHT_PULSES_DOUBLED];

        for (int i = 0; i < DHT_PULSES_DOUBLED; i++) {
            pulseCnt[i] = 0;
        }

        Gpio.pinMode(pin, Gpio.OUTPUT);
        Gpio.digitalWrite(pin, Gpio.HIGH);
        Gpio.delay(500);

        Gpio.pinMode(pin, Gpio.OUTPUT);
        Gpio.digitalWrite(pin, Gpio.LOW);
        Gpio.delay(20);

        Gpio.pinMode(pin, Gpio.INPUT);

        for (int i = 0; i < 50; i++) {
            //do nothing - just wait
        }

        //Wait until DHT pull pin down
        int count = 0;
        while(Gpio.digitalRead(pin) == Gpio.HIGH) {
            Gpio.delayMicroseconds(1);
            count++;
        }

        //Record pulse widths for a expected result bits
        for (int i = 0; i < DHT_PULSES_DOUBLED; i+=2) {
            //Count how long pin is in low state and store into pulseCnt[i]
            while(Gpio.digitalRead(pin) == Gpio.LOW) {
                Gpio.delayMicroseconds(1);
                if (++pulseCnt[i] >= DHT_MAX_COUNT) {
                    logger.warn("Error reading from DHT, pulseCnt lower than max count");
                    return;
                }
            }
            //Count how long pin is in low state and store into pulseCnt[i]
            while(Gpio.digitalRead(pin) == Gpio.HIGH) {
                Gpio.delayMicroseconds(1);
                if (++pulseCnt[i+1] >= DHT_MAX_COUNT) {
                    logger.warn("Error reading from DHT, timeout exceeded");
                    return;
                }
            }
        }

        int threshold = 0;
        for (int i = 2; i < DHT_PULSES_DOUBLED; i+=2) {
            threshold += pulseCnt[i];
        }

        threshold /= DHT_PULSES_DOUBLED - 1;

        // Interpret each high pulse as 0 or 1 by comparing to the 50us reference
        // When the count is less than 50us it must be a ~28us (LOW) pulse
        // when is higher it must be a ~70us (HIGH) pulse
        int[] data = {0, 0, 0, 0, 0};
        for (int i = 3; i < DHT_PULSES_DOUBLED; i+=2) {
            int index = (i - 3) / 16;
            data[index] <<= 1;
            if (pulseCnt[i] >= threshold) {
                //One bit for long pulse
                data[index] |= 1;
            } //Else zero bit for short pulse
        }
        logger.info("Measured values " + data[0] + " -- " + data[1] + " -- " + data[2] + " -- " + data[3]);
        //Verify checksum and set measured data
        if (checkParity(data)) {
            controlVal = 0;
            //TODO Check what about other bytes, since on stackoverflow are used
            // https://stackoverflow.com/questions/28486159/read-temperature-from-dht11-using-pi4j

            // data[0] - integral data[1] - decimal
            temperature = data[0];
            // data[2] - integral data[3] - decimal
            humidity = data[2];
            isError = false;

        } else {
            logger.warn("Checksum error");
        }
    }

    @Override
    public DHT getDht() {
        return new DHT(temperature, humidity, isError);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        logger.info("DHT sensor initialisation. DATA pin: " + pin + " supply pin: " + supplyPin);
        if (Gpio.wiringPiSetup() == -1) {
            logger.error("GPIO SETUP FAILED");
            return;
        }
        GpioUtil.export(supplyPin, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(supplyPin, Gpio.OUTPUT);
        Gpio.digitalWrite(supplyPin, Gpio.HIGH);
        GpioUtil.export(pin, GpioUtil.DIRECTION_OUT);
    }

    private boolean checkParity(int[] data) {
        return data[4] == ((data[0] + data[1] + data[2] + data[3]) & 0xFF);
    }

    /*
     float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
            if (h > 100) {
                h = dht11_dat[0]; // for DHT11
            }
            float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
            if (c > 125) {
                c = dht11_dat[2]; // for DHT11
            }
            if ((dht11_dat[2] & 0x80) != 0) {
                c = -c;
            }
            final float f = c * 1.8f + 32;
            System.out.println("Humidity = " + h + " Temperature = " + c + "(" + f + "f)");
        } else {
            System.out.println("Data not good, skip");
        }
     */

}
