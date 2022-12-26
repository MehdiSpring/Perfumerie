package com.mbo.perfumery.repository;

import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.model.Perfum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerfumRepository extends CrudRepository<Perfum, Long> {

    List<Perfum> findByCategory(Category category);
}
