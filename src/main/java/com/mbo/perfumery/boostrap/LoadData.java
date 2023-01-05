package com.mbo.perfumery.boostrap;

import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.model.Perfum;
import com.mbo.perfumery.repository.PerfumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final PerfumRepository perfumRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Perfum> perfums = Stream.of(new Perfum().builder().name("Parfum 1").description("Parfum de test").category(Category.Men).build(),
                  new Perfum().builder().name("Parfum 2").description("Parfum de test").category(Category.Women).build(),
                  new Perfum().builder().name("Parfum 3").description("Parfum de test").category(Category.Men).build(),
                  new Perfum().builder().name("Parfum 4").description("Parfum de test").category(Category.Women).build(),
                  new Perfum().builder().name("Parfum 5").description("Parfum de test").category(Category.Men).build()).collect(Collectors.toList());

        perfums.forEach(perfumRepository::save);

    }
}
