package com.mbo.perfumery.service;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.mapper.PerfumMapper;
import com.mbo.perfumery.model.Perfum;
import com.mbo.perfumery.repository.PerfumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    @Override
    public PerfumDto createPerfum(PerfumDto perfumDto) {
        return perfumMapper.perfumToPerfumDto(perfumRepository.save(perfumMapper.perfumDtoToPerfum(perfumDto)));
    }

    @Override
    public PerfumDto updatePerfum(UUID id, PerfumDto perfumDto) {

        Perfum perfumToUpdate = perfumRepository.findById(id).get();
        perfumToUpdate.setName(perfumDto.getName());
        perfumToUpdate.setPrice(perfumDto.getPrice());
        perfumToUpdate.setDescription(perfumDto.getDescription());
        perfumToUpdate.setIngredient(perfumDto.getIngredient());
        perfumToUpdate.setCategory(perfumDto.getCategory());

        return perfumMapper.perfumToPerfumDto(perfumRepository.save(perfumToUpdate));
    }

    @Override
    public void deletePerfum(UUID id) {

        perfumRepository.deleteById(id);
    }
}
