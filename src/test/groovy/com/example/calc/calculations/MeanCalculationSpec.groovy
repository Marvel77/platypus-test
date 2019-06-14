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
class MeanCalculationSpec extends Specification {

    def "should calculate mean value from several"() {

        given:
        MeanCalculation meanCalculation = new MeanCalculation()

        Stream.of(
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,3")
        )
        .forEach(new Consumer<TimeSeries>() {
            @Override
            void accept(TimeSeries timeSeries) {
                meanCalculation.apply(timeSeries)
            }
        })

        expect:
        new BigDecimal(2) == meanCalculation.calculate()
    }
}
