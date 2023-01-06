package com.mbo.perfumery.dto;

import com.mbo.perfumery.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfumDto {

    @Null
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String ingredient;

    @NotNull
    private Category category;

    @Null
    private Timestamp createdDate;

    @Null
    private Timestamp updatedDate;

    @NotNull
    @Positive
    private Double price;
}
