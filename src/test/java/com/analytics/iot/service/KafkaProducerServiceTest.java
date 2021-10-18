package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaProducerServiceTest {

    private KafkaProducerService service;

    @MockBean
    private  KafkaTemplate<String, SensorEvent> kafkaTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new KafkaProducerService(kafkaTemplate);
    }


    @Test
    public void testKafkaProducer(){
        SensorEvent event = new SensorEvent(1L, 1, new BigDecimal(10), OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        service.sendMessage(event);
        verify(kafkaTemplate, times(1)).send("iot-data",event);
    }

}
