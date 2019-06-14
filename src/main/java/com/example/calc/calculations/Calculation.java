package com.example.calc.calculations;

import java.math.BigDecimal;

import com.example.calc.TimeSeries;

/**
 *
 */
public interface Calculation {

    void apply(TimeSeries timeSeries);

    BigDecimal calculate();
}
