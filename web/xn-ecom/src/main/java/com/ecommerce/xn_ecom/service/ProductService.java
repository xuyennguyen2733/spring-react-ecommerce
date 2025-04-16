package com.ecommerce.xn_ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.xn_ecom.payload.ProductDTO;
import com.ecommerce.xn_ecom.payload.ProductResponse;

public interface ProductService {

  ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

  ProductResponse getAllProducts();

  ProductResponse findByCategory(Long categoryId);

  ProductResponse findByKeyword(String keyword);

  ProductDTO updateProduct(Long productId, ProductDTO productDTO);

  ProductDTO deleteProduct(Long productId);

  ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

}
