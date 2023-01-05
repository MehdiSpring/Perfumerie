package com.mbo.perfumery.repository;

import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.model.Perfum;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PerfumRepository extends PagingAndSortingRepository<Perfum, UUID> {

    List<Perfum> findByCategory(Category category);
}
