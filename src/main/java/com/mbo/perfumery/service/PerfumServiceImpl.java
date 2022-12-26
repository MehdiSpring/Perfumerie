package com.mbo.perfumery.service;

import com.mbo.perfumery.dto.ParfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.repository.PerfumeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfumeryServiceImpl implements PerfumeryService{

    private final PerfumeryRepository perfumeryRepository;

    @Override
    public List<ParfumDto> getAllParfum() {
        return null;
    }

    @Override
    public List<ParfumDto> getParfumByCategory(Category category) {
        return null;
    }
}
