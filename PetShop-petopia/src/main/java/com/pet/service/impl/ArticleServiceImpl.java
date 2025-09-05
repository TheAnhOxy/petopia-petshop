package com.pet.service.impl;

import com.pet.modal.request.ArticleRequestDTO;
import com.pet.modal.response.ArticleResponseDTO;
import com.pet.entity.Article;
import com.pet.entity.User;
import com.pet.repository.ArticleRepository;
import com.pet.repository.UserRepository;
import com.pet.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // Chuyển từ Entity -> DTO
    private ArticleResponseDTO mapToResponse(Article article) {
        return ArticleResponseDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .imageUrl(article.getImageUrl())
                .authorName(article.getAuthor() != null ? article.getAuthor().getFullName() : null)
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    @Override
    public ArticleResponseDTO createArticle(ArticleRequestDTO request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Article article = new Article();
        article.setArticleId("AR" + UUID.randomUUID().toString().substring(0, 8));
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setImageUrl(request.getImageUrl());
        article.setAuthor(author);

        return mapToResponse(articleRepository.save(article));
    }

    @Override
    public ArticleResponseDTO getArticleById(String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return mapToResponse(article);
    }

    @Override
    public List<ArticleResponseDTO> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponseDTO updateArticle(String id, ArticleRequestDTO request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setImageUrl(request.getImageUrl());

        if (request.getAuthorId() != null) {
            User author = userRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            article.setAuthor(author);
        }

        return mapToResponse(articleRepository.save(article));
    }

    @Override
    public void deleteArticle(String id) {
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article not found");
        }
        articleRepository.deleteById(id);
    }
}
