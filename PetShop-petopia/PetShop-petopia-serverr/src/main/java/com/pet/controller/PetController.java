package com.pet.controller;

import com.pet.entity.Pet;
import com.pet.modal.request.PetRequestDTO;
import com.pet.modal.request.PetSearchRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PetForListResponseDTO;
import com.pet.modal.response.PetResponseDTO;
import com.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@Slf4j
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/hello")
    @Operation(summary = "Test API", description = "Returns a test message")
    public String hello() {
        return "Hello Pet";
    }

    @GetMapping
    public List<Pet> getALlPets() {
        return petService.getPets();
    }

//    @Autowired
//    private ProductService productService;

//    @PostMapping
//    public ApiResponse createOrUpdateProduct(@RequestBody ProductRequestDTO productRequestDTO){
//        return ApiResponse.builder()
//                .status(HttpStatus.CREATED.value())
//                .message("Create product successfully")
//                .data(Map.of("productId", productService.createProduct(productRequestDTO).getProductId()))
//                .build();
//    }
//
//    @DeleteMapping("{id}")
//    public ApiResponse deleteProduct(@PathVariable String id){
//        productService.deleteProduct(id);
//        return ApiResponse.builder()
//                .status(ACCEPTED.value())
//                .message("Delete product successfully")
//                .build();
//    }
//
//    @PostMapping("/search")
//    public ResponseEntity<PageResponse<ProductDTO>> advanceSearch(@RequestBody ProductSearchRequest request) {
//        log.info("Search transaction");
//
//        request.validate();
//
//        System.out.println("Search transaction");
//
//        PageResponse<ProductDTO> result = productService.advanceSearch(request);
//
//        if (result.getContent() == null || result.getContent().isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable String id) {
        PetResponseDTO pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pet);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PageResponse<PetForListResponseDTO>> getPetsByCategory(
            @PathVariable String categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<PetForListResponseDTO> pets = petService.getPetsByCategory(categoryId, page, size);
        if (pets == null || pets.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pets);
    }

    // Phương thức dành cho admin
    @GetMapping("/list")
    public ResponseEntity<PageResponse<PetForListResponseDTO>> getAllPets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<PetForListResponseDTO> pets = petService.getAllPets(page, size);
        if (pets == null || pets.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pets);
    }

    // Phương thức dành cho web
    @GetMapping("/available")
    public ResponseEntity<PageResponse<PetForListResponseDTO>> getAllPetsWithStatusActive(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<PetForListResponseDTO> pets = petService.getAllPetsWithStatusActive(page, size);
        if (pets == null || pets.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/search")
    public ResponseEntity<?> advanceSearch(@RequestBody PetSearchRequestDTO request) {
        log.info("Search transaction");
        request.validate();
        PageResponse<PetForListResponseDTO> result = petService.advanceSearch(request);
        if (result.getContent() == null || result.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found any pet"));
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> addOrUpdatePet(@RequestBody PetRequestDTO petRequestDTO) {
        petRequestDTO.validate();
        PetResponseDTO pet = petService.addOrUpdatePet(petRequestDTO);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @PutMapping("/{id}/inactive")
    public ResponseEntity<PetResponseDTO> inactivePet(@PathVariable String id) {
        PetResponseDTO pet = petService.inactivePet(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pet);
    }

}
