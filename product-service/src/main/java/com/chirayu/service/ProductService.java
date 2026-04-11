package com.chirayu.service;

import com.chirayu.dto.ProductRequestDto;
import com.chirayu.dto.ProductResponseDto;
import com.chirayu.dto.ProductUpdateDto;
import com.chirayu.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface ProductService {

    ProductResponseDto findByProductId(Long productId);
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    ProductResponseDto updateStockQuantity(ProductUpdateDto productUpdateDto);
    ProductResponseDto updateProductDetails(Long productId,ProductRequestDto requestDto);
    Page<Product> findAllProduct(int page, int size, String sortBy, String sortDir);

    void saveProductsFromFile(MultipartFile file);
    Boolean isExists(Long productId);
    BigDecimal getProductPriceById(Long productId);
}
