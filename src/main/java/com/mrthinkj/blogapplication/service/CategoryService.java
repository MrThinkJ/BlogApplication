package com.mrthinkj.blogapplication.service;

import com.mrthinkj.blogapplication.payload.CategoryDTO;
import com.mrthinkj.blogapplication.payload.CategoryResponse;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(long id);
    CategoryResponse getAllCategory(int pageNo, int pageSize, String sortBy, String sortDir);
    CategoryDTO updateCategoryById(long id, CategoryDTO categoryDTO);
    void deleteCategoryById(long id);
}
