package com.example.calc.calculations;

import java.math.BigDecimal;

import com.example.calc.TimeSeries;

/**
 *
 */
public class MaxCalculation implements Calculation {

    private BigDecimal maximum = new BigDecimal(-1);

    @Override
    public void apply(TimeSeries timeSeries) {
        maximum = timeSeries.getValue().compareTo(maximum) > 0 ? timeSeries.getValue() : maximum;
    }

    @Override
    public BigDecimal calculate() {
        return maximum;
    }
}
