package com.pawbla.project.home.history.module.model;

import java.math.BigDecimal;

public interface StatsValue {

    String getTmpYear();
    String getTmpMonth();
    String getTmpDay();

    BigDecimal getMeanVal();
    BigDecimal getMinVal();
    BigDecimal getMaxVal();
}
