package com.analytics.iot.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SensorEventUtils {
    public static BigDecimal getAverageValue(List<BigDecimal> bigDecimals) {
        BigDecimal sum = bigDecimals.stream()
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(bigDecimals.size()));
    }


    public static BigDecimal getMedianValue(List<BigDecimal> listOfSensorValues) {
        int size = listOfSensorValues.size();
        if(size %2 == 0) {
            return getAverageValue(Arrays.asList(listOfSensorValues.get(size/2),listOfSensorValues.get((size/2)-1)));
        } else {
            return listOfSensorValues.get(size/2);
        }
    }

}
