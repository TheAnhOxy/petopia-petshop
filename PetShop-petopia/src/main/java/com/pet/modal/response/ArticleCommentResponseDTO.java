package com.pet.modal.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCommentResponseDTO {
    private String commentId;
    private String articleId;
    private String articleTitle;
    private String userId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
}
