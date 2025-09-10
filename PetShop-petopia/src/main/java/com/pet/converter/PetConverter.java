package com.pet.converter;

import com.pet.config.ModelMapperConfig;
import com.pet.entity.Category;
import com.pet.entity.Pet;
import com.pet.entity.PetImage;
import com.pet.enums.PetFurType;
import com.pet.enums.PetGender;
import com.pet.enums.PetStatus;
import com.pet.exception.ResourceNotFoundException;
import com.pet.modal.request.PetRequestDTO;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;
import com.pet.repository.CategoryRepository;
import com.pet.repository.PetImageRepository;
import com.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class PetConverter {
    @Autowired
    private ModelMapperConfig modelMapper;
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public PetForListResponseDTO mapToPetForListDTO(Pet pet) {
        return mapPet(pet, PetForListResponseDTO.class);
    }

    public PetResponseDTO mapToDTO(Pet pet) {
        System.out.println("ss");
        return mapPet(pet, PetResponseDTO.class);
    }

    public Pet mapToEntity(PetRequestDTO dto, Pet pet) {
        pet.setCategory(null); //Ngăn ModelMapper tự map category
        modelMapper.getModelMapper().map(dto, pet);

        if( pet.getPetId() != null) {
            pet.setStatus(PetStatus.valueOf(dto.getStatus().toUpperCase()));
        }else{
            pet.setPetId(generatePromotionId()); //Hoặc UUID.randomUUID().toString()
            pet.setStatus(PetStatus.AVAILABLE);
        }
        if (dto.getFurType() != null) {
            pet.setFurType(PetFurType.valueOf(dto.getFurType().toUpperCase()));
        }
        if (dto.getGender() != null) {
            pet.setGender(PetGender.valueOf(dto.getGender().toUpperCase()));
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
            pet.setCategory(category);
        }
        return pet;
    }

    private <T> T mapPet(Pet pet, Class<T> dtoClass) {
        T dto = modelMapper.getModelMapper().map(pet, dtoClass);

        Optional<PetImage> mainImageOpt = petImageRepository.findByIsThumbnailTrueAndPet_PetId(pet.getPetId());
        String mainImageUrl = mainImageOpt.map(PetImage::getImageUrl).orElse(null);

        if (dto instanceof PetForListResponseDTO) {
            PetForListResponseDTO petForListDTO = (PetForListResponseDTO) dto;
            petForListDTO.setMainImageUrl(mainImageUrl);
            petForListDTO.setRating(calculateAverageRating(pet));
            petForListDTO.setReviewCount(pet.getReviews() != null && !pet.getReviews().isEmpty() ? pet.getReviews().size() : 0);
        } else if (dto instanceof PetResponseDTO) {
            PetResponseDTO petDTO = (PetResponseDTO) dto;
            petDTO.setMainImageUrl(mainImageUrl);
            petDTO.setRating(calculateAverageRating(pet));
            petDTO.setReviewCount(pet.getReviews() != null && !pet.getReviews().isEmpty() ? pet.getReviews().size() : 0);
        }
        return dto;
    }

    private Double calculateAverageRating(Pet pet) {
        return pet.getReviews() != null && !pet.getReviews().isEmpty() ?
                pet.getReviews().stream()
                        .mapToDouble(review -> review.getRating() != null ? review.getRating() : 0)
                        .average()
                        .orElse(5.0)
                : 5.0;
    }

    private String generatePromotionId() {
        long count = petRepository.count() + 1;
        return String.format("P%03d", count);
    }
}
