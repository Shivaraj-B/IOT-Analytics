package com.analytics.iot.repository;

import com.analytics.iot.model.SensorEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface SensorEventRepository extends JpaRepository<SensorEvent, Long> {
       public List<SensorEvent> findBySensorId(long sensorId);

}
