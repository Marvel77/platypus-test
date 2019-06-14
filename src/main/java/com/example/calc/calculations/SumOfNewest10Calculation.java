package com.example.calc.calculations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.calc.TimeSeries;

/**
 *
 */
public class SumOfNewest10Calculation implements Calculation {

    private static final int FIRST = 0;

    private List<TimeSeries> timeSeriesList = new ArrayList<>(11);
    private BigDecimal sumOf10 = BigDecimal.ZERO;

    @Override
    public void apply(TimeSeries newElement) {

        int newElementPosition = 0;


        for (int i = 0; i < timeSeriesList.size(); i++) {

            TimeSeries nextElement = timeSeriesList.get(i);

            boolean isTimeSeriesDateIsGreater = newElement.compareTo(nextElement) > 0;
            if (isTimeSeriesDateIsGreater) {
                newElementPosition = i + 1;
            }

        }

        timeSeriesList.add(newElementPosition, newElement);
        sumOf10 = sumOf10.add(newElement.getValue());

        if (timeSeriesList.size() > 10) {

            sumOf10 = sumOf10.subtract(timeSeriesList.get(FIRST).getValue());
            timeSeriesList.remove(FIRST);
        }

    }

    @Override
    public BigDecimal calculate() {
        return sumOf10;
    }
}
