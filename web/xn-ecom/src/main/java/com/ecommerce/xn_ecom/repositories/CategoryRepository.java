package com.ecommerce.xn_ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.xn_ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByCategoryName(String categoryName);

}
