package com.example.calc.calculations;

import java.math.BigDecimal;
import java.time.YearMonth;

import com.example.calc.TimeSeries;

import lombok.NonNull;

/**
 *
 */
public class MeanForMonthCalculation implements Calculation {

    @NonNull private final YearMonth yearMonth;

    private Integer elementsCount = 0;
    private BigDecimal sum = new BigDecimal(0);

    public MeanForMonthCalculation(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    @Override
    public void apply(TimeSeries timeSeries) {
        if (yearMonth.equals(YearMonth.from(timeSeries.getDate()))) {
            elementsCount++;
            sum = sum.add(timeSeries.getValue());
        }
    }

    @Override
    public BigDecimal calculate() {
        return sum.divide(new BigDecimal(elementsCount));
    }
}
