package com.example.calc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.example.calc.calculations.MaxCalculation;
import com.example.calc.calculations.MeanCalculation;
import com.example.calc.calculations.MeanForMonthCalculation;
import com.example.calc.calculations.SumOfNewest10Calculation;
import com.example.calc.engine.TimeSeriesCalculationEngine;

import lombok.SneakyThrows;

/**
 *
 */
public class FinancialInstrumentReader {

    public static final String INSTRUMENT_1 = "INSTRUMENT1";
    public static final String INSTRUMENT_2 = "INSTRUMENT2";
    public static final String INSTRUMENT_3 = "INSTRUMENT3";

    public static void main(String[] args) {

        TimeSeriesCalculationEngine calculationEngine = new TimeSeriesCalculationEngine(new SumOfNewest10Calculation())
            .addCalculation(INSTRUMENT_1, new MeanCalculation())
            .addCalculation(INSTRUMENT_2, new MeanForMonthCalculation(YearMonth.of(2014, 11)))
            .addCalculation(INSTRUMENT_3, new MaxCalculation());

        parseAndCalculateFile("Financial_instruments_input.txt", calculationEngine);

        System.out.println("For " + INSTRUMENT_1 + " calculation is " + calculationEngine.calculate(INSTRUMENT_1));
        System.out.println("For " + INSTRUMENT_2 + " calculation is " + calculationEngine.calculate(INSTRUMENT_2));
        System.out.println("For " + INSTRUMENT_3 + " calculation is " + calculationEngine.calculate(INSTRUMENT_3));
        System.out.println("For the rest calculation is " + calculationEngine.calculateDefault());
    }

    @SneakyThrows
    public static void parseAndCalculateFile(String filename, TimeSeriesCalculationEngine calculationEngine) {

        int rowCount = 0;

        Path path = Paths.get(TimeSeriesUtils.class.getClassLoader()
            .getResource(filename).toURI());


        try (LineIterator it = FileUtils.lineIterator(FileUtils.getFile(path.toString()), "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                calculationEngine.apply(TimeSeriesUtils.from(line));
                rowCount++;
            }
        }

        System.out.println(rowCount + " rows has been parsed successfully");
    }
}
