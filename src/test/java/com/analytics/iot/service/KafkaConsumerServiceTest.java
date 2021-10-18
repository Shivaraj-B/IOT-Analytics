package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerServiceTest {
    @InjectMocks
    private KafkaConsumerService kafkaConsumerService = new KafkaConsumerService();
    @Mock
    private SensorEventRepository sensorEventRepository;

    @Test
    public void testKafkaConsumer(){
        SensorEvent event = new SensorEvent(1L, 1, new BigDecimal(10), OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        Mockito.when(sensorEventRepository.save(event)).thenReturn(event);
        kafkaConsumerService.consume(event);
        verify(sensorEventRepository, times(1)).save(event);
    }

}
