package com.ecommerce.xn_ecom.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.xn_ecom.config.AppConstants;
import com.ecommerce.xn_ecom.payload.CategoryDTO;
import com.ecommerce.xn_ecom.payload.CategoryResponse;
import com.ecommerce.xn_ecom.service.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/public/categories")
  public ResponseEntity<CategoryResponse> getAllCategories(
    @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(name = "sortedBy", defaultValue = AppConstants.SORT_CATEGORY_BY) String sortedBy,
    @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder) {
      
    CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortedBy, sortOrder);
    
    return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
  }

  @PostMapping("/public/categories")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
    CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
    return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
  }

  @PutMapping("/public/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> putMethodName(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
    CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
    return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);

  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) { 
    CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
    return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);

  }
  
}
