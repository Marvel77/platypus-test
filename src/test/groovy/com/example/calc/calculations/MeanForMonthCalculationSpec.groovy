package com.example.calc.calculations

import com.example.calc.TimeSeries
import com.example.calc.TimeSeriesUtils
import spock.lang.Specification

import java.time.YearMonth
import java.util.function.Consumer
import java.util.stream.Stream

/**
 *
 *
 */
class MeanForMonthCalculationSpec extends Specification {

    def "should calculate mean value for the specific month"() {

        given:
        MeanForMonthCalculation meanForMonthCalculation = new MeanForMonthCalculation(YearMonth.of(2015, 12))

        Stream.of(
                TimeSeriesUtils.from("INSTRUMENT1,12-Dec-2015,1"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Dec-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Dec-2015,2"),
                TimeSeriesUtils.from("INSTRUMENT1,12-Dec-2015,3")
        )
                .forEach(new Consumer<TimeSeries>() {
                    @Override
                    void accept(TimeSeries timeSeries) {
                        meanForMonthCalculation.apply(timeSeries)
                    }
                })

        meanForMonthCalculation.apply(TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,100"))

        expect:
        new BigDecimal(2) == meanForMonthCalculation.calculate()

    }
}
