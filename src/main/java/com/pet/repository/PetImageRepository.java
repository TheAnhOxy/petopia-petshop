package com.pet.repository;

import com.pet.entity.Pet;
import com.pet.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PetImageRepository extends JpaRepository<PetImage, String> {
    Optional<PetImage> findByIsThumbnailTrueAndPet_PetId(String petId);

    @Query("SELECT MAX(p.imageId) FROM PetImage p")
    String findMaxImageId();

    void deleteAllByPet_PetId(String petId);

}
