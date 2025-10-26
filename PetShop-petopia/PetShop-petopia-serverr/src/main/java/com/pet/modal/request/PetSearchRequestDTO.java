package com.pet.modal.request;

import lombok.Data;

@Data
public class PetSearchRequestDTO {
    private String name;
    private String categoryId;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
    private Double minPrice;
    private Double maxPrice;
    private String healthStatus;
    private Double minWeight;
    private Double maxWeight;
    private Double minHeight;
    private Double maxHeight;
    private Double minRating;
    private Boolean onSale; // Có giảm giá (discountPrice < price)
    private String color;
    private String furType;
    private Integer page;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection; // "asc" or "desc"
    private Double minFinalPrice;
    private Double maxFinalPrice;

    public void validate() {
        if(minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }
        if(minAge != null && maxAge != null && minAge > maxAge) {
            throw new IllegalArgumentException("minAge cannot be greater than maxAge");
        }
        if(minWeight != null && maxWeight != null && minWeight > maxWeight) {
            throw new IllegalArgumentException("minWeight cannot be greater than maxWeight");
        }
        if(minHeight != null && maxHeight != null && minHeight > maxHeight) {
            throw new IllegalArgumentException("minHeight cannot be greater than maxHeight");
        }
        if(minRating != null && (minRating < 0 || minRating > 5)) {
            throw new IllegalArgumentException("minRating must be between 0 and 5");
        }
        if(page != null && page < 0) {
            throw new IllegalArgumentException("page cannot be negative");
        }
        if(pageSize != null && pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be greater than 0");
        }
        if(sortDirection != null && !sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("sortDirection must be 'asc' or 'desc'");
        }
    }
}
