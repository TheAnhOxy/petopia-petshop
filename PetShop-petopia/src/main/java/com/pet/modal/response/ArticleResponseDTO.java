
package com.pet.modal.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
