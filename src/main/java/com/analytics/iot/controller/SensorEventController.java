package com.analytics.iot.controller;

import com.analytics.iot.config.CustomDeserializer;
import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.service.KafkaProducerService;
import com.analytics.iot.service.SensorEventService;
import com.analytics.iot.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/sensor")
public final class SensorEventController {
    private final SensorEventService sensorEventService;
    private static final Logger logger = LoggerFactory.getLogger(SensorEventController.class);

    public SensorEventController(SensorEventService sensorEventService) {
        this.sensorEventService = sensorEventService;
    }

    @GetMapping(value = "/{id}/average")
    public ResponseEntity<?> getSensorAverageData(@PathVariable("id") long id) {
        logger.info("Fetching average of sensor data for id {}", id);
        BigDecimal result = sensorEventService.getAverageDataForSensor(id);

        if (result == null) {
            logger.error("Could not get the average data for sensor with id: ", id);
            return new ResponseEntity(new CustomErrorType("Could not get the " +
                    "average data for sensor with id: " + id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/median")
    public ResponseEntity<?> getSensorMedianData(@PathVariable("id") long id) {
        logger.info("Fetching median of sensor data for id {}", id);
        BigDecimal result = sensorEventService.getMedianDataForSensor(id);

        if (result == null) {
            logger.error("Could not get the median data for sensor with id: ", id);
            return new ResponseEntity(new CustomErrorType("Could not get the " +
                    "median data for sensor with id: " + id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/min")
    public ResponseEntity<?> getSensorMinData(@PathVariable("id") long id) {
        logger.info("Fetching min of sensor data for id {}", id);
        BigDecimal result = sensorEventService.getMinDataForSensor(id);

        if (result == null) {
            logger.error("Could not get the min data for sensor with id: ", id);
            return new ResponseEntity(new CustomErrorType("Could not get the " +
                    "min data for sensor with id: " + id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/max")
    public ResponseEntity<?> getSensorMaxData(@PathVariable("id") long id) {
        logger.info("Fetching max of sensor data for id {}", id);
        BigDecimal result = sensorEventService.getMaxDataForSensor(id);

        if (result == null) {
            logger.error("Could not get the max data for sensor with id: ", id);
            return new ResponseEntity(new CustomErrorType("Could not get the " +
                    "max data for sensor with id: " + id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
