package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categoryList = new ArrayList<>();
    @Override
    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public String createCategory(Category category) {
        Long nextId = categoryList.isEmpty() ? 1 : categoryList.get(categoryList.size() - 1).getCategoryId() + 1;
        category.setCategoryId(nextId);
        categoryList.add(category);
        return "Category is created";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not found"));

        categoryList.remove(category);
        return "Category with categoryId " + categoryId + " not found!";
    }

    @Override
    public Category updateCategory(Category category, long categoryId) {
        Optional<Category> optionalCategory = categoryList.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

}
