package com.example.backend.service;

import com.example.backend.dto.ProductDto;
import com.example.backend.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<ProductDto> getProductsByName(String productName){

        return productMapper.getProductsByName(productName);
    }
}
