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
class MaxCalculationSpec extends Specification {

    def "should find the maximum value"() {
        given:
        MaxCalculation maxCalculation = new MaxCalculation()

        Stream.of(
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,3")
        )
                .forEach(new Consumer<TimeSeries>() {
                    @Override
                    void accept(TimeSeries timeSeries) {
                        maxCalculation.apply(timeSeries)
                    }
                })

        expect:
        new BigDecimal(3) == maxCalculation.calculate()
    }
}
