package com.pet.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDTO {
    private String petId;
    private String name;
    private String description;
    private String categoryName;
    private String categoryId;
    private Integer age;
    private String gender;
    private Double price;
    private Double discountPrice;
    private String healthStatus;
    private String vaccinationHistory;
    private Integer stockQuantity;
    private String status;
    private String videoUrl;
    private Double weight;
    private Double height;
    private String color;
    private String furType;
    private String mainImageUrl;
    private Double rating;
    private Integer reviewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PetImageResponseDTO> images;
}