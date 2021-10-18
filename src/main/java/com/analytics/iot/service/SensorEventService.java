package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import com.analytics.iot.utils.SensorEventUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class SensorEventService {

    private SensorEventRepository repo;

    @Autowired
    public SensorEventService(SensorEventRepository repo) {
        this.repo = repo;
    }

    private static final Logger logger = LoggerFactory.getLogger(SensorEventService.class);

    public BigDecimal getAverageDataForSensor(long id) {
        if(CollectionUtils.isEmpty(repo.findBySensorId(id))) {
            logger.warn("No sensor data found for id: "+id);
            return  null;
        }
        List<BigDecimal> listOfSensorValues = getSensorValues(id);
        return SensorEventUtils.getAverageValue(listOfSensorValues);
    }

    public BigDecimal getMedianDataForSensor(long id) {
        if(CollectionUtils.isEmpty(repo.findBySensorId(id))) {
            logger.warn("No sensor data found for id: "+id);
            return  null;
        }
        List<BigDecimal> listOfSensorValues = getSensorValues(id);
        return SensorEventUtils.getMedianValue(listOfSensorValues);
    }

    public BigDecimal getMinDataForSensor(long id) {
        if(CollectionUtils.isEmpty(repo.findBySensorId(id))) {
            logger.warn("No sensor data found for id: "+id);
            return  null;
        }

        SensorEvent minEvent= repo.findBySensorId(id)
                .stream()
                .min(Comparator.comparing(SensorEvent::getSensorValue))
                .orElse(null);
        return minEvent != null ? minEvent.getSensorValue() : null;
    }

    public BigDecimal getMaxDataForSensor(long id) {
        if(CollectionUtils.isEmpty(repo.findBySensorId(id))) {
            logger.warn("No sensor data found for id: "+id);
            return  null;
        }

        SensorEvent maxEvent=repo.findBySensorId(id)
                .stream()
                .max(Comparator.comparing(SensorEvent::getSensorValue))
                .orElse(null);
        return maxEvent != null ? maxEvent.getSensorValue() : null;
    }

    private  List<BigDecimal> getSensorValues(long id) {
        List<BigDecimal> listOfSensorValues = repo.findBySensorId(id)
                .stream()
                .map(e -> e.getSensorValue())
                .collect(Collectors.toList());
        return listOfSensorValues;
    }

}
