package com.mbo.perfumery.controller;

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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PerfumController.class)
@AutoConfigureRestDocs(uriPort = 8282)
@ExtendWith(RestDocumentationExtension.class)
class PerfumControllerTest {

    @MockBean
    PerfumService perfumService;

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
        mockMvc.perform(get("/api/v1/perfums")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.size()", equalTo(5)))
                .andDo(document("/api/v1/perfums",
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
        mockMvc.perform(get("/api/v1/perfums/byCategory")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("category", "Women"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.size()", equalTo(5)))
                .andDo(document("/api/v1/perfums/byCategory",
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

    List<PerfumDto> initSomePerfum()
    {

        return  Stream.of(new PerfumDto().builder().name("Parfum 1").description("Parfum de test").category(Category.Men).build(),
                new PerfumDto().builder().name("Parfum 2").description("Parfum de test").category(Category.Women).build(),
                new PerfumDto().builder().name("Parfum 3").description("Parfum de test").category(Category.Men).build(),
                new PerfumDto().builder().name("Parfum 4").description("Parfum de test").category(Category.Women).build(),
                new PerfumDto().builder().name("Parfum 5").description("Parfum de test").category(Category.Men).build()).collect(Collectors.toList());
    }
}