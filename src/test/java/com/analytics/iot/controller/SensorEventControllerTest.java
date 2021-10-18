package com.analytics.iot.controller;

import com.analytics.iot.service.SensorEventService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SensorEventController.class)
@AutoConfigureMockMvc
public class SensorEventControllerTest {

    @MockBean
    private SensorEventService service;

    private MockMvc mockMvc;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(new SensorEventController(service)).build();
    }

    @Test
    public void testGetSensorAverageData() throws Exception {
        Mockito.when(service.getAverageDataForSensor(1)).thenReturn(new BigDecimal(20));

        mockMvc.perform(get("/sensor/1/average"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(20)));
    }

    @Test
    public void testGetSensorAverageDataWithNull() throws Exception {
        Mockito.when(service.getAverageDataForSensor(1)).thenReturn(null);

        mockMvc.perform(get("/sensor/1/average"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetSensorMaxData() throws Exception {
        Mockito.when(service. getMaxDataForSensor(1)).thenReturn(new BigDecimal(20));

        mockMvc.perform(get("/sensor/1/max"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(20)));
    }

    @Test
    public void testGetSensorMinData() throws Exception {
        Mockito.when(service. getMinDataForSensor(1)).thenReturn(new BigDecimal(20));

        mockMvc.perform(get("/sensor/1/min"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(20)));
    }

    @Test
    public void testGetSensorMedianData() throws Exception {
        Mockito.when(service. getMedianDataForSensor(1)).thenReturn(new BigDecimal(20));

        mockMvc.perform(get("/sensor/1/median"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(20)));
    }



}
