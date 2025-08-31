package com.pet.service.converter;

import com.pet.config.ModelMapperConfig;
import com.pet.entity.Pet;
import com.pet.entity.PetImage;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;
import com.pet.repository.PetImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PetConverter {
    @Autowired
    private ModelMapperConfig modelMapper;
    @Autowired
    private PetImageRepository petImageRepository;

    public PetForListResponseDTO mapToPetForListDTO(Pet pet) {
        PetForListResponseDTO petForListResponseDTO = modelMapper.getModelMapper().map(pet, PetForListResponseDTO.class);

        Optional<PetImage> mainImageOpt = petImageRepository.findByIsThumbnailTrueAndPet_PetId(pet.getPetId());
        if (mainImageOpt.isPresent()) {
            petForListResponseDTO.setMainImageUrl(mainImageOpt.get().getImageUrl());
        } else {
            petForListResponseDTO.setMainImageUrl(null);
        }
        petForListResponseDTO.setRating(calculateAverageRating(pet));
        petForListResponseDTO.setReviewCount(pet.getReviews().size());

        return petForListResponseDTO;
    }

    public PetResponseDTO mapToDTO(Pet pet) {
        PetResponseDTO petResponseDTO = modelMapper.getModelMapper().map(pet, PetResponseDTO.class);

        Optional<PetImage> mainImageOpt = petImageRepository.findByIsThumbnailTrueAndPet_PetId(pet.getPetId());
        if (mainImageOpt.isPresent()) {
            petResponseDTO.setMainImageUrl(mainImageOpt.get().getImageUrl());
        } else {
            petResponseDTO.setMainImageUrl(null);
        }
        petResponseDTO.setRating(calculateAverageRating(pet));
        petResponseDTO.setReviewCount(pet.getReviews().size());

        return petResponseDTO;
    }

    private Double calculateAverageRating(Pet pet) {
        return pet.getReviews().stream()
                .mapToDouble(review -> review.getRating() != null ? review.getRating() : 0)
                .average()
                .orElse(5.0);
    }
}
