package com.ecommerce.xn_ecom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.xn_ecom.config.AppConstants;
import com.ecommerce.xn_ecom.payload.ProductDTO;
import com.ecommerce.xn_ecom.payload.ProductResponse;
import com.ecommerce.xn_ecom.service.ProductService;

import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping("/admin/categories/{categoryId}/product")
  public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long categoryId) {
      ProductDTO createdProduct = productService.addProduct(productDTO, categoryId);
      
      return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @GetMapping("/public/products")
  public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize, @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder, @RequestParam(name = "sortedBy", defaultValue = AppConstants.SORT_PRODUCT_BY) String sortedBy) {
    ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortedBy, sortOrder);

    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @GetMapping("/public/categories/{categoryId}/products")
  public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId, @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize, @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder, @RequestParam(name = "sortedBy", defaultValue = AppConstants.SORT_PRODUCT_BY) String sortedBy) {
    ProductResponse productResponse = productService.findByCategory(categoryId, pageNumber, pageSize, sortedBy, sortOrder);

    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @GetMapping("/public/products/keyword/{keyword}")
  public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword, @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize, @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder, @RequestParam(name = "sortedBy", defaultValue = AppConstants.SORT_PRODUCT_BY) String sortedBy) {
      ProductResponse productResponse = productService.findByKeyword(keyword, pageNumber, pageSize, sortedBy, sortOrder);

      return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
  }
  
  @PutMapping("/public/products/{productId}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDTO productDTO) {
    ProductDTO updatedProductDto = productService.updateProduct(productId, productDTO);
    
    return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
  }

  @DeleteMapping("admin/products/{productId}")
  public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
    ProductDTO deletedProductDto = productService.deleteProduct(productId);

    return new ResponseEntity<>(deletedProductDto, HttpStatus.OK);
  }

  @PutMapping("/public/{productId}/image")
  public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId, @RequestParam("image") MultipartFile image) throws IOException {
    ProductDTO updatedProductDTO = productService.updateProductImage(productId, image);

    return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
  }
}
