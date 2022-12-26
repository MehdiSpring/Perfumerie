package com.mbo.perfumery.service;

import com.mbo.perfumery.dto.ParfumDto;
import com.mbo.perfumery.enums.Category;

import java.util.List;

public interface PerfumeryService {

    List<ParfumDto> getAllParfum();
    List<ParfumDto> getParfumByCategory(Category category);

}
