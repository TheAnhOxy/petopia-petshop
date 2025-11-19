package com.pet.modal.response;

import lombok.Data;

@Data
public class PetImageResponseDTO {
    private String imageId;
    private String imageUrl;
    private Boolean isThumbnail;
}