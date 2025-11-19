package com.pet.modal.response;

import com.pet.enums.PetFurType;
import com.pet.enums.PetGender;
import com.pet.enums.PetStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PetDetailResponseDTO {
    private String petId;
    private String name;
    private String description;
    private String categoryId;
    private Integer age;
    private PetGender gender;
    private Double price;
    private Double discountPrice;
    private String healthStatus;
    private String vaccinationHistory;
    private Integer stockQuantity;
    private PetStatus status;
    private String videoUrl;
    private Double weight;
    private Double height;
    private String color;
    private PetFurType furType;
    private List<String> imageUrls;
}