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

    private static final int MAXTIMINGS  = 85;

    private final int[] data = { 0, 0, 0, 0, 0 };

    private final int pin;
    private final int supplyPin;
    private int temperature;
    private int humidity;
    private boolean isError;

    public DHTReader(@Value("${custom.dhtDataPin}") int pin, @Value("${custom.dhtSupplyPin}") int supplyPin) {
        this.pin = pin;
        this.supplyPin = supplyPin;
        this.isError = true;
    }

    @Override
    public void read() {
        logger.info("Read data from DHT sensor");
        int laststate = Gpio.HIGH;
        int j = 0;

        isError = true;
        data[0] = data[1] = data[2] = data[3] = data[4] = 0;

        Gpio.pinMode(pin, Gpio.OUTPUT);
        Gpio.digitalWrite(pin, Gpio.LOW);
        Gpio.delay(18);

        Gpio.digitalWrite(pin, Gpio.HIGH);
        Gpio.pinMode(pin, Gpio.INPUT);

        for (int i = 0; i < MAXTIMINGS; i++) {
            int counter = 0;
            while (Gpio.digitalRead(pin) == laststate) {
                counter++;
                Gpio.delayMicroseconds(1);
                if (counter == 255) {
                    break;
                }
            }

            laststate = Gpio.digitalRead(pin);

            if (counter == 255) {
                break;
            }

            /* ignore first 3 transitions */
            if (i >= 4 && i % 2 == 0) {
                /* shove each bit into the storage bytes */
                data[j / 8] <<= 1;
                if (counter > 16) {
                    data[j / 8] |= 1;
                }
                j++;
            }
        }

        logger.info("Measured values data[0]: " + data[0] + " data[1]: " + data[1] + " data[2]: " + data[2] +
                " data[3]: " + data[3] + " data[4]: " + data[4]);

        if (j >= 40 && checkParity(data) && checkNotZero(data)) {
            final float humidityFloat = (float) ((data[0] << 8) + data[1]) / 10;
            final float temperatureFloat = (float) (((data[2] & 0x7F) << 8) + data[3]) / 10;
            humidity = Math.round(humidityFloat);
            if ((data[2] & 0x80) != 0) {
                temperature = -Math.round(temperatureFloat);
            } else {
                temperature = Math.round(temperatureFloat);
            }
            isError = false;
        } else {
            logger.warn("Checksum or all values zero error");
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

    private boolean checkNotZero(int[] data) {
        return data[0] != 0 || data[1] != 0 || data[2] != 0 || data[3] != 0 || data[4] != 0;
    }
}
