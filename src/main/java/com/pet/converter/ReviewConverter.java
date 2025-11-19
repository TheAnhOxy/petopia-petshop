package com.pet.converter;

import com.pet.config.ModelMapperConfig;
import com.pet.entity.Review;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.ReviewResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    @Autowired
    private ModelMapperConfig modelMapper;

    public ReviewResponseDTO toResponseDTO(Review review) {
        ReviewResponseDTO dto = modelMapper.getModelMapper().map(review, ReviewResponseDTO.class);
        dto.setReviewImageUrl(review.getImageUrl());

        if (review.getUser() != null) {
            dto.setUserId(review.getUser().getUserId());
            dto.setUserFullName(review.getUser().getFullName());
            dto.setUserAvatar(review.getUser().getAvatar());
        }
        if (review.getPet() != null) {
            dto.setPetId(review.getPet().getPetId());
            dto.setPetName(review.getPet().getName());
            if (review.getPet().getImages() != null && !review.getPet().getImages().isEmpty()) {
                dto.setPetImage(review.getPet().getImages().iterator().next().getImageUrl());
            }
        }
        return dto;
    }

    public PageResponse<ReviewResponseDTO> toPageResponse(Page<Review> reviewPage) {
        List<ReviewResponseDTO> dtos = reviewPage.getContent().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        PageResponse<ReviewResponseDTO> response = new PageResponse<>();
        response.setContent(dtos);
        response.setPage(reviewPage.getNumber());
        response.setSize(reviewPage.getSize());
        response.setTotalElements(reviewPage.getTotalElements());
        return response;
    }
}