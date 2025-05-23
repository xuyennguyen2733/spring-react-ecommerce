package com.ecommerce.xn_ecom.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
  private String productName;
  private String description;
  
  private Integer quantity;
  private String image;
  private double price;
  private double specialPrice;
  private double discount;
  
  @ManyToOne
  @JoinColumn(name = "category_id")
  @NotBlank
  private Category category;
}
