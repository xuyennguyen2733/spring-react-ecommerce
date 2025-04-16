package com.ecommerce.xn_ecom.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.xn_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.xn_ecom.model.Category;
import com.ecommerce.xn_ecom.model.Product;
import com.ecommerce.xn_ecom.payload.ProductDTO;
import com.ecommerce.xn_ecom.payload.ProductResponse;
import com.ecommerce.xn_ecom.repositories.CategoryRepository;
import com.ecommerce.xn_ecom.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    Product product = modelMapper.map(productDTO, Product.class);

    product.setImage("default.png");
    product.setCategory(category);
    double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
    product.setSpecialPrice(specialPrice);
    
    Product savedProduct = productRepository.save(product);

    return modelMapper.map(savedProduct, ProductDTO.class);
  }

  @Override
  public ProductResponse getAllProducts() {
    List<Product> products = productRepository.findAll();
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    return new ProductResponse(productDTOs);
  }

  @Override
  public ProductResponse findByCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    return new ProductResponse(productDTOs);
  }

  @Override
  public ProductResponse findByKeyword(String keyword) {
    List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
    
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    return new ProductResponse(productDTOs);
  }

  @Override
  public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
    
    Product productFromDB = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

    Product product = modelMapper.map(productDTO, Product.class);

    productFromDB.setProductName(product.getProductName());
    productFromDB.setDescription(product.getDescription());
    productFromDB.setQuantity(product.getQuantity());
    productFromDB.setDiscount(product.getDiscount());
    productFromDB.setPrice(product.getPrice());

    double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
    product.setSpecialPrice(specialPrice);

    Product savedProduct = productRepository.save(productFromDB);
    
    return modelMapper.map(savedProduct, ProductDTO.class);
  }

  @Override
  public ProductDTO deleteProduct(Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

    productRepository.delete(product);

    return modelMapper.map(product, ProductDTO.class);
  }

  @Override
  public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

    String path = "/images";
    String fileName = uploadImage(path, image);
    
        product.setImage(fileName);
        Product savedProduct = productRepository.save(product);
    
        return modelMapper.map(savedProduct, ProductDTO.class);
    
    
      }
    
      private String uploadImage(String path, MultipartFile image) throws IOException {
        String originalFileName = image.getName();

        String randomId = UUID.randomUUID().toString();

        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.pathSeparator + fileName;

        File folder = new File(path);
        
        if (!folder.exists()) {
          folder.mkdir();
        }

        Files.copy(image.getInputStream(), Paths.get(filePath));

        return fileName;
      }

}
