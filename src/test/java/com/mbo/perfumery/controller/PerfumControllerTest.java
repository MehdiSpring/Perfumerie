package com.mbo.perfumery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.service.PerfumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PerfumController.class)
@AutoConfigureRestDocs(uriPort = 8282)
@ExtendWith(RestDocumentationExtension.class)
class PerfumControllerTest {

    public static final String API_V1_PERFUMS = "/api/v1/perfums";
    @MockBean
    PerfumService perfumService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    List<PerfumDto> perfumDtos;

    @BeforeEach
    void setUp() {
        perfumDtos = initSomePerfum();
    }

    @Test
    void getAllPerfums() throws Exception {

        //When
        when(perfumService.getAllParfum()).thenReturn(perfumDtos);

        //Then
        mockMvc.perform(get(API_V1_PERFUMS)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.size()", equalTo(5)))
                .andDo(document("get"+API_V1_PERFUMS,
                        responseFields(
                                fieldWithPath("[].name").description("Perfum's name"),
                                fieldWithPath("[].description").description("A brief description about the Perfum"),
                                fieldWithPath("[].category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("[].price").description("Perfum's price")
                        )));
    }

    @Test
    void getPerfumsByCategory() throws Exception {

        //When
        when(perfumService.getParfumByCategory(any())).thenReturn(perfumDtos);

        //Then
        mockMvc.perform(get(API_V1_PERFUMS + "/byCategory")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("category", "Women"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.size()", equalTo(5)))
                .andDo(document("get"+API_V1_PERFUMS + "/byCategory",
                        requestParameters(
                                parameterWithName("category").description("Perfum's category to lock for")
                        ),
                        responseFields(
                                fieldWithPath("[].name").description("Perfum's name"),
                                fieldWithPath("[].description").description("A brief description about the Perfum"),
                                fieldWithPath("[].category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("[].price").description("Perfum's price")
                        )));
    }

    @Test
    void createPerfum() throws Exception {
        //Given
        PerfumDto perfumDto = new PerfumDto().builder()
                .name("Perfum 1")
                .description("Test's perfum")
                .category(Category.Men).build();

        String jsonPerfum = objectMapper.writeValueAsString(perfumDto);

        //When
        when(perfumService.createPerfum(any())).thenReturn(perfumDto);

        //Then
        mockMvc.perform(post(API_V1_PERFUMS)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerfum))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(perfumDto.getName())))
                .andDo(document("post"+API_V1_PERFUMS,
                        requestFields(
                                fieldWithPath("name").description("Perfum's name"),
                                fieldWithPath("description").description("A brief description about the Perfum"),
                                fieldWithPath("category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("price").description("Perfum's price")
                        ),
                        responseFields(
                                fieldWithPath("name").description("Perfum's name"),
                                fieldWithPath("description").description("A brief description about the Perfum"),
                                fieldWithPath("category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("price").description("Perfum's price")
                        )));

    }

    @Test
    void updatePerfum() throws Exception {
        //Given
        PerfumDto perfumDto = new PerfumDto().builder()
                .name("Perfum 1")
                .description("Test's perfum")
                .category(Category.Men).build();

        String jsonPerfum = objectMapper.writeValueAsString(perfumDto);

        //When
        when(perfumService.updatePerfum(anyLong(), any())).thenReturn(perfumDto);

        //Then
        mockMvc.perform(put(API_V1_PERFUMS+"/{id}", 5)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerfum))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name", equalTo(perfumDto.getName())))
                .andDo(document("put"+API_V1_PERFUMS,
                        pathParameters(
                                parameterWithName("id").description("The perfum'Id to update")
                        ),
                        requestFields(
                                fieldWithPath("name").description("Perfum's name"),
                                fieldWithPath("description").description("A brief description about the Perfum"),
                                fieldWithPath("category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("price").description("Perfum's price")
                        ),
                        responseFields(
                                fieldWithPath("name").description("Perfum's name"),
                                fieldWithPath("description").description("A brief description about the Perfum"),
                                fieldWithPath("category").description("Defines if the perfum is for Men or Women"),
                                fieldWithPath("price").description("Perfum's price")
                        )));
    }

    @Test
    void deletePerfum() throws Exception {
        //Then, When
        mockMvc.perform(delete(API_V1_PERFUMS+"/{id}", 6))
                .andExpect(status().isAccepted())
                .andDo(document("delete"+API_V1_PERFUMS,
                        pathParameters(
                                parameterWithName("id").description("Perfum's Id to delete")
                        )));
    }

    List<PerfumDto> initSomePerfum()
    {

        return  Stream.of(new PerfumDto().builder().name("Parfum 1").description("Parfum de test").category(Category.Men).build(),
                new PerfumDto().builder().name("Parfum 2").description("Parfum de test").category(Category.Women).build(),
                new PerfumDto().builder().name("Parfum 3").description("Parfum de test").category(Category.Men).build(),
                new PerfumDto().builder().name("Parfum 4").description("Parfum de test").category(Category.Women).build(),
                new PerfumDto().builder().name("Parfum 5").description("Parfum de test").category(Category.Men).build()).collect(Collectors.toList());
    }
}