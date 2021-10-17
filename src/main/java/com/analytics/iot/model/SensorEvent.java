package com.analytics.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.*;

@Entity
@Table(name = "sensorEvent")
public class SensorEvent {
    @Id
    @Column(name = "sensorPkey")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long sensorPkey;

    @Column(name = "sensorId", nullable = false)
    private long sensorId;

    @Column(name = "sensorValue", nullable = false)
    private BigDecimal sensorValue;

    @Column(name = "eventTimestamp", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssxxx")
    private OffsetDateTime eventTimestamp;

    @Column(name = "sensorType", nullable = false)
    private String sensorType;

    @Column(name = "sensorName", nullable = false)
    private String sensorName;

    @Column(name = "clustorId", nullable = true)
    private long clustorId;

    public SensorEvent(Long sensorPkey, long sensorId, BigDecimal sensorValue,
                       OffsetDateTime eventTimestamp, String sensorType, String sensorName,
                       long clustorId) {
        this.sensorPkey = sensorPkey;
        this.sensorId = sensorId;
        this.sensorValue = sensorValue;
        this.eventTimestamp = eventTimestamp;
        this.sensorType = sensorType;
        this.sensorName = sensorName;
        this.clustorId = clustorId;
    }

    public SensorEvent() {
    }

    public Long getSensorPkey() {
        return sensorPkey;
    }

    public void setSensorPkey(Long sensorPkey) {
        this.sensorPkey = sensorPkey;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public BigDecimal getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(BigDecimal sensorValue) {
        this.sensorValue = sensorValue;
    }

    public OffsetDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(OffsetDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public long getClustorId() {
        return clustorId;
    }

    public void setClustorId(long clustorId) {
        this.clustorId = clustorId;
    }
}
