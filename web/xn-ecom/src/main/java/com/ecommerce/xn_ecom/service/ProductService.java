package com.ecommerce.xn_ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.xn_ecom.payload.ProductDTO;
import com.ecommerce.xn_ecom.payload.ProductResponse;

public interface ProductService {

  ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

  ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder);

  ProductResponse findByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder);

  ProductResponse findByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder);

  ProductDTO updateProduct(Long productId, ProductDTO productDTO);

  ProductDTO deleteProduct(Long productId);

  ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

}
