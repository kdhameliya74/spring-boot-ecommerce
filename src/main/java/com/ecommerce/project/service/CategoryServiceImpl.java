package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categoryList = new ArrayList<>();
    @Override
    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        // Category lastCategory = categoryList.getLast();
        Long nextId = categoryList.isEmpty() ? 1 : categoryList.get(categoryList.size() - 1).getCategoryId() + 1;
        category.setCategoryId(nextId);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElse(null);
        if(category != null) {
            categoryList.remove(category);
            return "Category with categoryId " + categoryId + " deleted successfully";
        }
        return "Category with categoryId " + categoryId + " not found!";
    }
}
