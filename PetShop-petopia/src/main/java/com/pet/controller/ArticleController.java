package com.pet.controller;

import com.pet.modal.request.ArticleRequestDTO;
import com.pet.modal.response.ArticleResponseDTO;
import com.pet.modal.response.ApiResponse;
import com.pet.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponse> createArticle(@RequestBody ArticleRequestDTO request) {
        ArticleResponseDTO response = articleService.createArticle(request);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Article created successfully")
                .data(response)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getArticleById(@PathVariable String id) {
        ArticleResponseDTO response = articleService.getArticleById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Article retrieved successfully")
                .data(response)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllArticles() {
        List<ArticleResponseDTO> responses = articleService.getAllArticles();
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Articles retrieved successfully")
                .data(responses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateArticle(@PathVariable String id,
                                                     @RequestBody ArticleRequestDTO request) {
        ArticleResponseDTO response = articleService.updateArticle(id, request);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Article updated successfully")
                .data(response)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Article deleted successfully")
                .build());
    }
}
