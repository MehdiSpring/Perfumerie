package com.mbo.perfumery.service;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.mapper.PerfumMapper;
import com.mbo.perfumery.model.Perfum;
import com.mbo.perfumery.repository.PerfumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerfumServiceImplTest {

    @Mock
    PerfumRepository perfumRepository;

    PerfumMapper perfumMapper;

    PerfumService perfumService;

    List<Perfum> perfums;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        perfumMapper = new PerfumMapper();
        perfumService = new PerfumServiceImpl(perfumRepository, perfumMapper);

        perfums = this.initSomePerfum();
    }

    @Test
    void getAllParfum() {

        //Given
        List<PerfumDto> perfumDtos = new ArrayList<>();

        //When
        when(perfumRepository.findAll()).thenReturn(perfums);

        //Then
        perfumDtos = perfumService.getAllParfum();
        assertEquals(perfums.size(), perfumDtos.size());
    }

    @Test
    void getParfumByCategory() {

        //Given
        List<PerfumDto> perfumDtos = new ArrayList<>();

        //When
        when(perfumRepository.findByCategory(any())).thenReturn(perfums);

        //Then
        perfumDtos = perfumService.getParfumByCategory(Category.Men);
        assertEquals(perfums.size(), perfumDtos.size());
    }

    List<Perfum> initSomePerfum()
    {

        return  Stream.of(new Perfum().builder().name("Parfum 1").description("Parfum de test").category(Category.Men).build(),
                new Perfum().builder().name("Parfum 2").description("Parfum de test").category(Category.Women).build(),
                new Perfum().builder().name("Parfum 3").description("Parfum de test").category(Category.Men).build(),
                new Perfum().builder().name("Parfum 4").description("Parfum de test").category(Category.Women).build(),
                new Perfum().builder().name("Parfum 5").description("Parfum de test").category(Category.Men).build()).collect(Collectors.toList());
    }
}