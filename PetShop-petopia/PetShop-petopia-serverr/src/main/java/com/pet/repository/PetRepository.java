package com.pet.repository;

import com.pet.entity.Pet;
import com.pet.enums.PetStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, String>, JpaSpecificationExecutor<Pet> {
    Page<Pet> findByCategory_CategoryId(String categoryId, Pageable pageable);
    Page<Pet> findAllByStatus(PetStatus status, Pageable pageable);
}
