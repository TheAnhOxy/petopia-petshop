package com.pet.controller;

import com.pet.entity.Pet;
import com.pet.service.PetService;
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


}
