package com.mbo.perfumery.dto;

import com.mbo.perfumery.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParfumDto {

    private String name;
    private String description;
    private Category category;
    private Double price;
}
