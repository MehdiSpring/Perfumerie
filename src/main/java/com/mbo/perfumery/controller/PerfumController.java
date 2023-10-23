package com.mbo.perfumery.controller;

import com.mbo.perfumery.dto.PerfumDto;
import com.mbo.perfumery.enums.Category;
import com.mbo.perfumery.service.PerfumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//This is a comment from branch_1
//This is a comment from branch_3
//This is a comment from branch_4
//This is first commit from branch_5
//This is third commit from branch_5
//Try a commit from branch_6
//Try a commit from branch_7 (Snake)
//Another commit from branch_6
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

    @GetMapping("/{id}")
    public ResponseEntity<PerfumDto> getPerfumById(@PathVariable UUID id)
    {
        return new ResponseEntity<>(perfumService.getPerfumById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<PerfumDto> createPerfum(@Valid @RequestBody PerfumDto perfumDto)
    {
        return new ResponseEntity<>(perfumService.createPerfum(perfumDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfumDto> updatePerfum(@PathVariable UUID id, @Valid @RequestBody PerfumDto perfumDto)
    {
        return new ResponseEntity<>(perfumService.updatePerfum(id, perfumDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerfum(@PathVariable UUID id)
    {
        perfumService.deletePerfum(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
