package com.pet.modal.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentRequestDTO {
    private String userId;     // ID người comment
    private String articleId;  // ID bài viết
    private String content;    // Nội dung comment
}
