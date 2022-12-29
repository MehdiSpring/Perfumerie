package com.mbo.perfumery.controller;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.service.PerfumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<PerfumDto> createPerfum(@RequestBody PerfumDto perfumDto)
    {
        return new ResponseEntity<>(perfumService.createPerfum(perfumDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfumDto> updatePerfum(@PathVariable String id, @RequestBody PerfumDto perfumDto)
    {
        return new ResponseEntity<>(perfumService.updatePerfum(Long.valueOf(id), perfumDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerfum(@PathVariable Long id)
    {
        perfumService.deletePerfum(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
