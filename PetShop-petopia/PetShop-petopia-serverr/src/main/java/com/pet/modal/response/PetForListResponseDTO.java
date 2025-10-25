package com.pet.modal.response;

import com.pet.enums.PetGender;
import com.pet.enums.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetForListResponseDTO {
    private String petId;
    private String name;
    private String description;
    private Double price;
    private Long discountPrice;
    private Integer stockQuantity;
    private String mainImageUrl;
    private Double rating;
    private Integer reviewCount;
    private Long totalSold;
    private String categoryName;
    private PetGender gender;
    private String healthStatus;
    private PetStatus status;
}
