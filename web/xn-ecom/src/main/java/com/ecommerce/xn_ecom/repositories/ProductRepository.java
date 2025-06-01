package com.ecommerce.xn_ecom.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.xn_ecom.model.Category;
import com.ecommerce.xn_ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Page<Product> findByCategory(Category category, Pageable pageable);

  Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);

  Product getByProductName(String productName);
}
