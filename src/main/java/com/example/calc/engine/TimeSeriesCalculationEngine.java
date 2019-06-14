package com.example.calc.engine;

import java.math.BigDecimal;
import java.util.HashMap;

import com.example.calc.TimeSeries;
import com.example.calc.calculations.Calculation;

import lombok.NonNull;

/**
 *
 */
public class TimeSeriesCalculationEngine {

    public TimeSeriesCalculationEngine(Calculation defaultCalculation) {
        this.defaultCalculation = defaultCalculation;
    }

    private HashMap<String, Calculation> calculations = new HashMap<>();
    @NonNull private Calculation defaultCalculation;


    public void apply(TimeSeries timeSeries) {
        calculations.getOrDefault(timeSeries.getInstrumentName(), defaultCalculation).apply(timeSeries);
    }


    public BigDecimal calculate(String instrumentName) {
        return calculations.get(instrumentName).calculate();
    }

    public BigDecimal calculateDefault() {
        return defaultCalculation.calculate();
    }

    public TimeSeriesCalculationEngine addCalculation(String instrumentName, Calculation calculation) {
        calculations.put(instrumentName, calculation);
        return this;
    }


}
