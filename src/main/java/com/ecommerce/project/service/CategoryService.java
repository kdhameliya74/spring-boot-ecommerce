package com.ecommerce.project.service;

import com.ecommerce.project.dto.category.response.CategoryResponse;
import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategoryList();

    String createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, long categoryId);
}
