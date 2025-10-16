package com.pet.modal.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponseDTO {
    private String articleId;
    private String title;
    private String content;
    private String imageUrl;
    private String authorId;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔥 Danh sách bình luận
    private List<ArticleCommentResponseDTO> comments;
}
