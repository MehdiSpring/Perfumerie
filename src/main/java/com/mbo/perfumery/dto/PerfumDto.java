package com.mbo.perfumery.dto;

import com.mbo.perfumery.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfumDto {

    private UUID id;
    private String name;
    private String description;
    private String ingredient;
    private Category category;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Double price;
}
