package com.pet.repository;

import com.pet.entity.Pet;
import com.pet.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetImageRepository extends JpaRepository<PetImage, String> {
    Optional<PetImage> findByIsThumbnailTrueAndPet_PetId(String petId);
}
