package com.example.calc.calculations

import com.example.calc.TimeSeries
import com.example.calc.TimeSeriesUtils
import spock.lang.Specification

import java.util.function.Consumer
import java.util.stream.Stream

/**
 *
 *
 */
class SumOfNewest10CalculationSpec extends Specification {

    def "should calculate the sum of the 10 newest elements (in terms of the date)"() {
        given:
        SumOfNewest10Calculation sumOfNewest10Calculation = new SumOfNewest10Calculation()

        Stream.of(

                TimeSeriesUtils.from("INSTRUMENT1,11-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,18-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,13-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,10-Mar-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,14-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,15-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,16-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,17-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,19-Mar-2015,1")
        )
                .forEach(new Consumer<TimeSeries>() {
                    @Override
                    void accept(TimeSeries timeSeries) {
                        sumOfNewest10Calculation.apply(timeSeries)
                    }
                })

        sumOfNewest10Calculation.apply(TimeSeriesUtils.from("INSTRUMENT1,20-Mar-2015,1"))

        expect:
        sumOfNewest10Calculation.calculate() == new BigDecimal(10)

    }
}
