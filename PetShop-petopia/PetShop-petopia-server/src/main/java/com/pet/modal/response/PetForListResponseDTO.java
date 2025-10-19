package com.pet.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //Dùng khi load nhiều Pet về để hiển thị danh sách
public class PetForListResponseDTO {
    private String petId;
    private String name;
    private String description;
    private Long price;
    private Long discountPrice;
    private Integer stockQuantity;
    private String mainImageUrl;
    private Double rating;
    private Integer reviewCount;
}
