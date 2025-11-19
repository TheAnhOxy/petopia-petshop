package com.pet.modal.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private String reviewId;

    private String userId;
    private String userFullName;
    private String userAvatar;

    private String petId;
    private String petName;
    private String petImage;

    private Integer rating;
    private String comment;
    private String reviewImageUrl;
    private LocalDateTime createdAt;

    private String reply;
    private LocalDateTime replyDate;
}