package com.ccdb.smartstrawberryfarm.environmentinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnvironmentInfoControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEnvironmentInfo() throws Exception {

        EnvironmentInfoDto environmentInfoDto = EnvironmentInfoDto.builder()
                .farmname("gyFarm")
                .area("gyArea")
                .brightness(44.2)
                .humidity(100)
                .temperature(64.2)
                .build();

        mockMvc.perform(post("/api/environmentinfo")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(environmentInfoDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }


    //입력받을수 없는 입력이 주어진 경우
    @Test
    public void createEnvironmentInfo_Bad_Request_INSERT_ID() throws Exception {

        EnvironmentInfo environmentInfo = EnvironmentInfo.builder()
                .id(10L)
                .farmname("gyFarm")
                .area("gyArea")
                .brightness(44.2)
                .humidity(52)
                .temperature(64.2)
                .build();

        mockMvc.perform(post("/api/environmentinfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(environmentInfo)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //비어있는 입력이 주어진 경우
    @Test
    public void createEnvironmentInfo_Bad_Request_Empty() throws Exception {

        EnvironmentInfoDto environmentInfoDto = EnvironmentInfoDto.builder().build();

        mockMvc.perform(post("/api/environmentinfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(environmentInfoDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //잘못된 입력이 주어진 경우
    @Test
    public void createEnvironmentInfo_Bad_Request_Wrong() throws Exception {

        EnvironmentInfoDto environmentInfoDto = EnvironmentInfoDto.builder()
                .farmname("gyFarm")
                .area("gyArea")
                .brightness(44.2)
                .humidity(-50)
                .temperature(64.2)
                .build();

        mockMvc.perform(post("/api/environmentinfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(environmentInfoDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        environmentInfoDto = EnvironmentInfoDto.builder()
                .farmname("gyFarm")
                .brightness(44.2)
                .humidity(101)
                .temperature(64.2)
                .build();

        mockMvc.perform(post("/api/environmentinfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(environmentInfoDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }



}