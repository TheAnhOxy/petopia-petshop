package com.pet.modal.response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor //Dùng khi load chi tiết 1 Pet
public class PetResponseDTO {
    private String petId;
    private String name;
    private String description;
    private Integer age;
    private String gender;
    private Long price;
    private Long discountPrice;
    private String healthStatus;
    private String vaccinationHistory;
    private Integer stockQuantity;
    private String mainImageUrl;
    private String videoUrl;
    private Double weight;
    private Double height;
    private String color;
    private String furType;
    private Double rating;
    private Integer reviewCount;

}
