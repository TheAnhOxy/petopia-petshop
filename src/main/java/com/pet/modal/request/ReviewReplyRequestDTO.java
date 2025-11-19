package com.pet.modal.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewReplyRequestDTO {
    @NotBlank(message = "Nội dung trả lời không được để trống")
    private String replyContent;
}