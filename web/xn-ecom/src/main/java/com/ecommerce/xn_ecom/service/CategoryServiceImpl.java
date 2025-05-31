package com.ecommerce.xn_ecom.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.xn_ecom.exceptions.APIException;
import com.ecommerce.xn_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.xn_ecom.model.Category;
import com.ecommerce.xn_ecom.payload.CategoryDTO;
import com.ecommerce.xn_ecom.payload.CategoryResponse;
import com.ecommerce.xn_ecom.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder) {
    Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
    
    Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
    Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

    List<Category> categories = categoryPage.getContent();
    if (categories.isEmpty()) {
      throw new APIException("Such empty :((");
    }

    List<CategoryDTO> categoryDTOs = categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

    CategoryResponse categoryResponse = new CategoryResponse();
    categoryResponse.setContent(categoryDTOs);
    categoryResponse.setPageNumber(categoryPage.getNumber());
    categoryResponse.setPageSize(categoryPage.getSize());
    categoryResponse.setTotalElements(categoryPage.getTotalElements());
    categoryResponse.setTotalPages(categoryPage.getTotalPages());
    categoryResponse.setLastPage(categoryPage.isLast());


    return categoryResponse;
  }

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    Category category = modelMapper.map(categoryDTO, Category.class);
    Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
    if (categoryFromDb != null) throw new APIException("Category with the name " + category.getCategoryName() + " already exists!");
    Category savedCategory = categoryRepository.save(category);
    return modelMapper.map(savedCategory, CategoryDTO.class);

  }

  @Override
  public CategoryDTO deleteCategory(Long categoryId) {

    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryName", categoryId));

    categoryRepository.delete(category);

    return modelMapper.map(category, CategoryDTO.class);
  }

  @Override
  public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) { 
    Category categoryFromDb = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryName", categoryId));
    categoryDTO.setId(categoryFromDb.getId());
    Category category = modelMapper.map(categoryDTO, Category.class);
    Category savedCategory = categoryRepository.save(category);
    return modelMapper.map(savedCategory, CategoryDTO.class);
    
  }

}
