package com.mbo.perfumery.mapper;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.model.Perfum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PerfumMapper {

    public PerfumDto perfumToPerfumDto(Perfum perfum)
    {
        PerfumDto perfumDto = new PerfumDto();
        BeanUtils.copyProperties(perfum, perfumDto);
        return perfumDto;
    }

    public Perfum perfumDtoToPerfum(PerfumDto perfumDto)
    {
        Perfum perfum = new Perfum();
        BeanUtils.copyProperties(perfumDto, perfum);
        return perfum;
    }
}
