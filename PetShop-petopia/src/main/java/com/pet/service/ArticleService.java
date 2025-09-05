package com.pet.service;

import com.pet.modal.request.ArticleRequestDTO;
import com.pet.modal.response.ArticleResponseDTO;
import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO request);
    ArticleResponseDTO getArticleById(String id);
    List<ArticleResponseDTO> getAllArticles();
    ArticleResponseDTO updateArticle(String id, ArticleRequestDTO request);
    void deleteArticle(String id);
}
