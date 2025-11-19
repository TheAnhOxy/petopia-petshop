package com.pet.modal.request;

import lombok.Data;

@Data
public class PetImageDTO {
    private String id;
    private String imageUrl;
    private Boolean isThumbnail;
}