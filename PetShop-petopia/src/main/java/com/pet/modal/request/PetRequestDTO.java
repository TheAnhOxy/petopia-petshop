package com.pet.modal.request;
import lombok.Data;

@Data
public class PetRequestDTO {
    private String petId;
    private String name;
    private String description;
    private String categoryId;
    private Integer age;
    private String gender;
    private Double price;
    private Double discountPrice;
    private String status;
    private String healthStatus;
    private String vaccinationHistory;
    private Integer stockQuantity;
    private String videoUrl;
    private Double weight;
    private Double height;
    private String color;
    private String furType;

    public void validate() {
        if (price != null && price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        if (discountPrice != null && discountPrice < 0) {
            throw new IllegalArgumentException("discountPrice cannot be negative");
        }
        if (age != null && age < 0) {
            throw new IllegalArgumentException("age cannot be negative");
        }
        if (stockQuantity != null && stockQuantity < 0) {
            throw new IllegalArgumentException("stockQuantity cannot be negative");
        }
        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("weight cannot be negative");
        }
        if (height != null && height < 0) {
            throw new IllegalArgumentException("height cannot be negative");
        }
        if (categoryId != null && categoryId.isEmpty()) {
            throw new IllegalArgumentException("categoryId cannot be empty if provided");
        }
    }
}
