package com.pet.service.impl;

import com.pet.entity.Category;
import com.pet.entity.Pet;
import com.pet.enums.PetFurType;
import com.pet.enums.PetGender;
import com.pet.enums.PetStatus;
import com.pet.exception.ResourceNotFoundException;
import com.pet.modal.request.PetRequestDTO;
import com.pet.modal.request.PetSearchRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;
import com.pet.repository.CategoryRepository;
import com.pet.repository.PetRepository;
import com.pet.repository.spec.PetSpecification;
import com.pet.service.PetService;
import com.pet.converter.PetConverter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
        Pet pet = petRepository.findById(petId).orElse(null);
        if (pet == null) {
            return null;
        }
        return petConverter.mapToDTO(pet);
    }

    @Override
    public List<PetForListResponseDTO> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(petConverter::mapToPetForListDTO)
                .toList();
    }

    @Override
    public PageResponse<PetForListResponseDTO> advanceSearch(PetSearchRequestDTO request) {
        Specification<Pet> spec = new PetSpecification(request);
        String sortField = getSortField(request.getSortBy());
        Sort sort = Sort.by(request.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), sort);

        Page<Pet> petPage = petRepository.findAll(spec, pageable);
        List<PetForListResponseDTO> petDTOs = petPage.getContent().stream()
                .map(petConverter::mapToPetForListDTO)
                .toList();

        PageResponse<PetForListResponseDTO> response = new PageResponse<>();
        response.setContent(petDTOs);
        response.setPage(petPage.getNumber());
        response.setSize(petPage.getSize());
        response.setTotalElements(petPage.getTotalElements());
//        log.info("Total elements found: {}", petPage.getTotalElements());
        return response;
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

    private String getSortField(String sortBy) {
        return switch (sortBy) {
            case "name" -> "name";
            case "price" -> "price";
            case "rating" -> "rating";
            default -> "createdAt";
        };
    }

}
