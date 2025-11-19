package com.pet.modal.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull(message = "Pet ID không được để trống")
    private String petId;
    private String userId;

    @Min(1) @Max(5)
    private Integer rating;

    private String comment;
    private String imageUrl;
}