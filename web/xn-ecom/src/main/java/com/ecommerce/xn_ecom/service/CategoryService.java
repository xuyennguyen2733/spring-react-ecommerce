package com.ecommerce.xn_ecom.service;

import com.ecommerce.xn_ecom.payload.CategoryDTO;
import com.ecommerce.xn_ecom.payload.CategoryResponse;

import jakarta.validation.Valid;

public interface CategoryService {
  CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder);
  CategoryDTO createCategory(CategoryDTO categoryDTO);
  CategoryDTO deleteCategory(Long categoryId);
  CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
