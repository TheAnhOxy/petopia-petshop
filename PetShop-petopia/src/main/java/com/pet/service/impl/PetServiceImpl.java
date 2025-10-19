package com.pet.service.impl;

import com.pet.entity.Pet;
import com.pet.enums.PetStatus;
import com.pet.exception.ResourceNotFoundException;
import com.pet.modal.request.PetRequestDTO;
import com.pet.modal.request.PetSearchRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;
import com.pet.repository.PetRepository;
import com.pet.repository.spec.PetSpecification;
import com.pet.service.PetService;
import com.pet.converter.PetConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetConverter petConverter;

    @Override
    public List<Pet> getPets() {
        List<Pet> pets = petRepository.findAll();
        return pets;
    }

    @Override
    public PetResponseDTO getPetById(String petId) {
        return petRepository.findById(petId)
                .map(petConverter::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
    }

    @Override
    public PageResponse<PetForListResponseDTO> getPetsByCategory(String categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Pet> petPage = petRepository.findByCategory_CategoryId(categoryId, pageable);
        return petConverter.toPageResponse(petPage);
    }

    @Override
    public PageResponse<PetForListResponseDTO> getAllPets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Pet> petPage = petRepository.findAll(pageable);
        return petConverter.toPageResponse(petPage);
    }

    @Override
    public PageResponse<PetForListResponseDTO> getAllPetsWithStatusActive(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Pet> petPage = petRepository.findAllByStatus(PetStatus.AVAILABLE, pageable);
        return petConverter.toPageResponse(petPage);
    }

    @Override
    public PageResponse<PetForListResponseDTO> advanceSearch(PetSearchRequestDTO request) {
        Specification<Pet> spec = new PetSpecification(request);
        String sortField = getSortField(request.getSortBy());
        Sort sort = Sort.by(request.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), sort);

        Page<Pet> petPage = petRepository.findAll(spec, pageable);
        return petConverter.toPageResponse(petPage);
    }

    @Override
    @Transactional
    public PetResponseDTO addOrUpdatePet(PetRequestDTO requestDTO) {
        Pet pet;
        if (requestDTO.getPetId() != null) {
            pet = petRepository.findById(requestDTO.getPetId())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + requestDTO.getPetId()));
            pet.setUpdatedAt(LocalDateTime.now());
        } else {
            pet = new Pet();
            pet.setCreatedAt(LocalDateTime.now());
        }
        pet = petConverter.mapToEntity(requestDTO, pet);
        Pet savedPet = petRepository.save(pet);
        return petConverter.mapToDTO(savedPet);
    }

    // Phương thức xóa mềm (soft delete) bằng cách đặt trạng thái pet thành DRAFT
    @Override
    @Transactional
    public PetResponseDTO inactivePet(String petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        pet.setStatus(PetStatus.DRAFT);
        pet.setUpdatedAt(LocalDateTime.now());
        Pet savedPet = petRepository.save(pet);
        return petConverter.mapToDTO(savedPet);
    }

    private String getSortField(String sortBy) {
        return switch (sortBy) {
            case "name" -> "name";
            case "price" -> "price";
            case "rating" -> "rating";
            default -> "createdAt";
        };
    }
}