package com.ecommerce.xn_ecom.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.xn_ecom.exceptions.APIException;
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
  
  @Autowired FileService fileService;
  
  @Value("${project.image}")
  private String path;

  @Override
  public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {
    // validations
    // 1. Check if a product already exists
    
    Product productFromDb = productRepository.getByProductName(productDTO.getProductName());
    
    if (productFromDb != null) throw new APIException("Product with name " + productDTO.getProductName() + " already exists!");
    
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
  public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortedBy, String sortOrder) {
    // Validations [optional]: check if products list is empty
    
    Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
    
    Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
    
    Page<Product> productPage = productRepository.findAll(pageDetails);
    
    List<Product> products = productPage.getContent();
    
    // if (products.isEmpty()) {
    //   throw new APIException("Such empty :((");
    // }
    
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    ProductResponse productResponse = new ProductResponse();
    productResponse.setContent(productDTOs);
    productResponse.setPageNumber(productPage.getNumber());
    productResponse.setPageSize(productPage.getSize());
    productResponse.setTotalElements(productPage.getTotalElements());
    productResponse.setTotalPages(productPage.getTotalPages());
    productResponse.setLastPage(productPage.isLast());
    
    return productResponse;
  }

  @Override
  public ProductResponse findByCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    // Validations [optional]: check if products list is empty
    
    List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
    
    // if (products.isEmpty()) {
    //   throw new APIException("Such empty :((");
    // }
    
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    ProductResponse productResponse = new ProductResponse();
    productResponse.setContent(productDTOs);
    
    return productResponse;
  }

  @Override
  public ProductResponse findByKeyword(String keyword) {
    // validation [optional]: check if products list is empty
    
    List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
    
    // if (products.isEmpty()) {
    //   throw new APIException("Such empty :((");
    // }
    
    List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

    ProductResponse productResponse = new ProductResponse();
    productResponse.setContent(productDTOs);
    
    return productResponse;
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

    String fileName = fileService.uploadImage(path, image);
    
        product.setImage(fileName);
        Product savedProduct = productRepository.save(product);
    
        return modelMapper.map(savedProduct, ProductDTO.class);
    
    
      }

}
