package com.ecommerce.project.service;

import com.ecommerce.project.dto.category.request.CategoryCreateRequest;
import com.ecommerce.project.dto.category.response.CategoryResponse;
import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()) {
            throw new APIException("No any category has been created yet");
        }
        List<CategoryCreateRequest> categoryCreateRequests = categoryList.stream().map(category -> modelMapper.map(category, CategoryCreateRequest.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryCreateRequests);
        return categoryResponse;
    }

    @Override
    public String createCategory(Category category) {
        Category existedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existedCategory != null) {
            throw new APIException("Category with name " + existedCategory.getCategoryName() + " already existed");
        }
        categoryRepository.save(category);
        return "Category is created";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(savedCategory);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }

}
