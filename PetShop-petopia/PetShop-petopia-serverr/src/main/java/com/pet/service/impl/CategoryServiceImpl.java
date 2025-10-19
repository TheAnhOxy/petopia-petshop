package com.pet.service.impl;

import com.pet.converter.CategoryConverter;
import com.pet.modal.response.CategoryResponseDTO;
import com.pet.repository.CategoryRepository;
import com.pet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryConverter::mapToDTO)
                .toList();
    }
}
