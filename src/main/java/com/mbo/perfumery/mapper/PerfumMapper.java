package com.mbo.perfumery.mapper;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.model.Perfum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PerfumMapper {

    public PerfumDto perfumToPerfumDto(Perfum perfum);
    public Perfum perfumDtoToPerfum(PerfumDto perfumDto);

}
