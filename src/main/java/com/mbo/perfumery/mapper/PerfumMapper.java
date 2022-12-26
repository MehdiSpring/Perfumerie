package com.mbo.perfumery.mapper;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.model.Perfum;
import org.springframework.stereotype.Component;

@Component
public class PerfumMapper {

    PerfumDto parfumToParfumDto(Perfum perfum)
    {
        return new PerfumDto()
                .builder()
                .name(perfum.getName())
                .description(perfum.getDescription())
                .category(perfum.getCategory())
                .price(perfum.getPrice())
                .build();
    }

    Perfum parfumDtoToParfum(PerfumDto perfumDto)
    {
        return new Perfum()
                .builder()
                .name(perfumDto.getName())
                .description(perfumDto.getDescription())
                .category(perfumDto.getCategory())
                .price(perfumDto.getPrice())
                .build();
    }
}
