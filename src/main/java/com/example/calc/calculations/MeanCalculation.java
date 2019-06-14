package com.example.calc.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.calc.TimeSeries;

/**
 *
 */
public class MeanCalculation implements Calculation {

    private Integer elementsCount = 0;
    private BigDecimal sum = new BigDecimal(0);

    @Override
    public void apply(TimeSeries timeSeries) {
        elementsCount++;
        sum = sum.add(timeSeries.getValue());
    }

    @Override
    public BigDecimal calculate() {
        return sum.divide(new BigDecimal(elementsCount), RoundingMode.FLOOR);
    }
}
