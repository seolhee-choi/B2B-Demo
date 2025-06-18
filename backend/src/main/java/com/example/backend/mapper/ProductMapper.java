package com.example.backend.mapper;

import com.example.backend.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDto> getProductsByName(String productName);
}
