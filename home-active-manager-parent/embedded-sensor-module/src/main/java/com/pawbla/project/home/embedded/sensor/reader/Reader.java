package com.pawbla.project.home.embedded.sensor.reader;

public interface Reader {
    int read(int type, int pin);
    Object getDht();
}
