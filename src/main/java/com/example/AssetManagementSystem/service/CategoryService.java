package com.example.AssetManagementSystem.service;


import com.example.AssetManagementSystem.entity.Category;
import com.example.AssetManagementSystem.exception.CategoryManagementException;
import com.example.AssetManagementSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }

    public Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElseThrow(() -> new CategoryManagementException("Category not found with ID: " + categoryId));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isEmpty()) {
            throw new CategoryManagementException("Category not found with ID: " + categoryId);
        }

        Category category = existingCategory.get();
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
