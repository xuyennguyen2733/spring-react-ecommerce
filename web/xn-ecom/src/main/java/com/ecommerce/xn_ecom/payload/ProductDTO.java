package com.ecommerce.xn_ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private Long id;
  private String productName;
  private String image;
  private Integer quantity;
  private String description;
  private double price;
  private double discount;
  private double specialPrice;
}
