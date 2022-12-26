package com.mbo.perfumery.boostrap;

import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.model.Parfum;
import com.mbo.perfumery.repository.ParfumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final ParfumRepository parfumRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Parfum> parfums = Stream.of(new Parfum().builder().name("Parfum 1").description("Parfum de test").category(Category.Men).build(),
                  new Parfum().builder().name("Parfum 2").description("Parfum de test").category(Category.Women).build(),
                  new Parfum().builder().name("Parfum 3").description("Parfum de test").category(Category.Men).build(),
                  new Parfum().builder().name("Parfum 4").description("Parfum de test").category(Category.Women).build(),
                  new Parfum().builder().name("Parfum 5").description("Parfum de test").category(Category.Men).build()).collect(Collectors.toList());

        parfums.forEach(parfumRepository::save);

    }
}
