package com.example.calc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 */
public class TimeSeriesUtils {

    private static DateTimeFormatter TIME_SERIES_LOCAL_DATE = DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.US);


    public static TimeSeries from(String timeSeriesCSV) {
        String[] timeSeries = timeSeriesCSV.split(",");

        if (timeSeries.length < 3) throw new IllegalArgumentException("String value of the Time Series should contain Instrument Name, date and value separated by commas");

        return new TimeSeries(timeSeries[0], LocalDate.parse(timeSeries[1], TIME_SERIES_LOCAL_DATE), new BigDecimal(timeSeries[2]));
    }


}
