package com.ecommerce.xn_ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
  private Integer pageNumber;
  private Integer pageSize;
  private Long totalElements;
  private Integer totalPages;
  private boolean lastPage;
}
