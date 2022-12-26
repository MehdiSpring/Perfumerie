package com.mbo.perfumery.service;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.mapper.PerfumMapper;
import com.mbo.perfumery.model.Perfum;
import com.mbo.perfumery.repository.PerfumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfumServiceImpl implements PerfumService {

    private final PerfumRepository perfumRepository;
    private final PerfumMapper perfumMapper;

    @Override
    public List<PerfumDto> getAllParfum() {

        List<Perfum> perfumList = new ArrayList<>();

        this.perfumRepository.findAll().forEach(perfumList::add);

        return perfumList.stream().map(perfumMapper::perfumToPerfumDto).collect(Collectors.toList());
    }

    @Override
    public List<PerfumDto> getParfumByCategory(Category category) {

        return perfumRepository.findByCategory(category).stream().map(perfumMapper::perfumToPerfumDto).collect(Collectors.toList());
    }
}
