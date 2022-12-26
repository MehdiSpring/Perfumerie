package com.mbo.perfumery.mapper;

import com.mbo.perfumery.dto.ParfumDto;
import com.mbo.perfumery.model.Parfum;
import org.springframework.stereotype.Component;

@Component
public class ParfumMapper {

    ParfumDto parfumToParfumDto(Parfum parfum)
    {
        return new ParfumDto()
                .builder()
                .name(parfum.getName())
                .description(parfum.getDescription())
                .category(parfum.getCategory())
                .price(parfum.getPrice())
                .build();
    }

    Parfum parfumDtoToParfum(ParfumDto parfumDto)
    {
        return new Parfum()
                .builder()
                .name(parfumDto.getName())
                .description(parfumDto.getDescription())
                .category(parfumDto.getCategory())
                .price(parfumDto.getPrice())
                .build();
    }
}
