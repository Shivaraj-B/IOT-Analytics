package com.analytics.iot.repository;

import com.analytics.iot.model.SensorEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface SensorEventRepository extends JpaRepository<SensorEvent, Long> {

}
