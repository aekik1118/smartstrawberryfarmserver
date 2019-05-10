package com.ccdb.smartstrawberryfarm.environmentinfo;

import com.ccdb.smartstrawberryfarm.common.RestDocsConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.time.LocalDate;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
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
                .temperature(64.3)
                .build();

        System.out.println(objectMapper.writeValueAsString(environmentInfoDto));

        mockMvc.perform(post("/api/environmentinfo")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(environmentInfoDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andDo(document("create-environment-info",
                                links(
                                        linkWithRel("self").description("link to self"),
                                        linkWithRel("query-events").description("link to query-events")
                                ),
                                requestHeaders(
                                        headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("contnet type header")
                                ),
                                requestFields(
                                        fieldWithPath("farmname").description("농장의 이름"),
                                        fieldWithPath("area").description("farm 농장의 구역의 이름"),
                                        fieldWithPath("humidity").description("해당 구역의 습도 정보"),
                                        fieldWithPath("temperature").description("해당 구역의 온도 정보"),
                                        fieldWithPath("brightness").description("해당 구역의 밝기 정보")
                                )
                        ));
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

    @Test
    public void getInfoList() throws Exception {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        LocalDate exDate = currentDate.minusWeeks(1);

        mockMvc.perform(get("/api/environmentinfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaTypes.HAL_JSON)
            .param("farmName","gyFarm")
            .param("area","gyArea")
            .param("beginTime",exDate.toString())
            .param("endTime",currentDate.toString())
        )
                .andDo(print())
                .andDo(document("get-events-list",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("contnet type header")
                        ),
                        requestParameters(
                                parameterWithName("farmName").description("농장의 이름"),
                                parameterWithName("area").description("farm 농장의 구역의 이름"),
                                parameterWithName("beginTime").description("조회하려는 기간의 시작 날짜 : YYYY-MM-DD 형식으로 보내야 한다"),
                                parameterWithName("endTime").description("조회하려는 기간의 마지막 날짜 + 1 : YYYY-MM-DD 형식으로 보내야 한다")
                        )
                ));


    }



}