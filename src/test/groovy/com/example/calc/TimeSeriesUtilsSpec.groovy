package com.example.calc


import spock.lang.Specification

import java.time.LocalDate

class TimeSeriesUtilsSpec extends Specification {


    def "should read time series from string"() {
        expect:
        TimeSeriesUtils.from("INSTRUMENT1,12-Mar-2015,12.21") == new TimeSeries("INSTRUMENT1",
                LocalDate.of(2015, 3, 12),
                new BigDecimal("12.21"))
    }

    def "should parse time series from a big file"() {
        expect:
        int rowNumber = TimeSeriesUtils.parseAndCalculateFile("Financial_instruments_input.txt")
        14826 == rowNumber
    }


    def "should run calculations on time series from a big file"() {
        expect:
        int rowNumber = TimeSeriesUtils.parseAndCalculateFile("Financial_instruments_input.txt")
        14826 == rowNumber
    }

}