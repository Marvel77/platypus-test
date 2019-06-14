package com.example.calc;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NonNull;

/**
 *
 */
@Data
public class TimeSeries implements Comparable<TimeSeries> {
    @NonNull private String instrumentName;
    @NonNull private LocalDate date;
    @NonNull private BigDecimal value;

    @Override
    public int compareTo(@NonNull TimeSeries timeSeries) {
        return date.compareTo(timeSeries.getDate());
    }
}
