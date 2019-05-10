package com.ccdb.smartstrawberryfarm.pollution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PollutionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void createPollution() throws Exception {

        PollutionDto pollutionDto = PollutionDto.builder()
                .farmname("gyFarm")
                .area("gyArea")
                .isPollution(true)
                .build();

        mockMvc.perform(post("/api/pollution")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(pollutionDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void isPollution() throws Exception {
        mockMvc.perform(get("/api/pollution")
            .param("farmName","gyFarm")
            .param("area","gyArea"))
                .andDo(print())
                .andExpect(content().string("true"));
    }

}