package com.ecommerce.xn_ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long productId;
  
  @NotBlank
  @Size(min = 3, message = "Product name must contain at least 3 characters")
  private String productName;
  
  @NotBlank
  @Size(min = 6, message = "Product description must contain at least 6 characters")
  private String description;
  
  private Integer quantity;
  private String image;
  private double price;
  private double specialPrice;
  private double discount;
  
  @ManyToOne
  @JoinColumn(name = "category_id")
  @NotNull
  private Category category;
}
