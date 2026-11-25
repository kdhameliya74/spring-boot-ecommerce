package com.ecommerce.project.service;

import com.ecommerce.project.dto.category.request.CategoryDTO;
import com.ecommerce.project.dto.category.response.CategoryResponse;
import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategoryList();

    CategoryDTO createCategory(CategoryDTO categoryCreateRequest);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, long categoryId);
}
