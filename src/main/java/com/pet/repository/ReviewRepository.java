package com.pet.repository;

import com.pet.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Review> findByPet_PetIdOrderByCreatedAtDesc(String petId, Pageable pageable);
    @Query("SELECT r FROM Review r WHERE r.reply IS NULL OR r.reply = ''")
    Page<Review> findByReplyIsNull(Pageable pageable);
    @Query("SELECT r.reviewId FROM Review r ORDER BY r.reviewId DESC LIMIT 1")
    Optional<String> findLastReviewId();
}