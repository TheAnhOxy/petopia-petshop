package com.pet.service;

import com.pet.entity.Pet;
import com.pet.modal.request.PetRequestDTO;
import com.pet.modal.request.PetSearchRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;

import java.util.List;

public interface PetService {

    List<Pet> getPets();

    PetResponseDTO getPetById(String petId);
    List<PetForListResponseDTO> getAllPets();
    PageResponse<PetForListResponseDTO> advanceSearch(PetSearchRequestDTO request);
    PetResponseDTO addOrUpdatePet(PetRequestDTO request);
}
