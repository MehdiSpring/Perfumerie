package com.mbo.perfumery.controller;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.service.PerfumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/perfums")
public class PerfumController {

    private final PerfumService perfumService;

    @GetMapping
    public ResponseEntity<List<PerfumDto>> getAllPerfums()
    {
        return new ResponseEntity<>(perfumService.getAllParfum(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<PerfumDto>> getPerfumsByCategory(@RequestParam Category category)
    {
        return new ResponseEntity<>(perfumService.getParfumByCategory(category), HttpStatus.ACCEPTED);
    }
}
