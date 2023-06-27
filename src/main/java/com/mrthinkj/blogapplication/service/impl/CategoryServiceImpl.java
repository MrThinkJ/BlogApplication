package com.mrthinkj.blogapplication.service.impl;

import com.mrthinkj.blogapplication.entity.Category;
import com.mrthinkj.blogapplication.exception.ResourceNotFoundException;
import com.mrthinkj.blogapplication.payload.CategoryDTO;
import com.mrthinkj.blogapplication.payload.CategoryResponse;
import com.mrthinkj.blogapplication.repository.CategoryRepository;
import com.mrthinkj.blogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return mapToDTO(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(category);
    }

    @Override
    public CategoryResponse getAllCategory(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page page = categoryRepository.findAll(pageable);

        CategoryResponse categoryResponse = new CategoryResponse();
        List<Category> categories = page.getContent();
        categoryResponse.setContent(categories.stream().map(category -> mapToDTO(category)).collect(Collectors.toList()));
        categoryResponse.setTotalPage(page.getTotalPages());
        categoryResponse.setTotalElements(page.getTotalElements());
        categoryResponse.setPageSize(page.getSize());
        categoryResponse.setPageNo(page.getNumber());
        categoryResponse.setLastPage(page.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO updateCategoryById(long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category updateCategory = categoryRepository.save(category);
        return mapToDTO(updateCategory);
    }

    @Override
    public void deleteCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }

    private CategoryDTO mapToDTO(Category category){
        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    private Category mapToEntity(CategoryDTO categoryDTO){
        Category category = mapper.map(categoryDTO, Category.class);
        return category;
    }
}
