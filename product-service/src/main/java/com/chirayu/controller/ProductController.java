package com.chirayu.controller;

import com.chirayu.dto.ProductRequestDto;
import com.chirayu.dto.ProductResponseDto;
import com.chirayu.dto.ProductUpdateDto;
import com.chirayu.entity.Product;
import com.chirayu.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto findById(@PathVariable("productId") Long productId){
        return productService.findByProductId(productId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }

    @PostMapping("/stock")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto updateStockQuantity(@Valid @RequestBody ProductUpdateDto productUpdateDto){
        return productService.updateStockQuantity(productUpdateDto);
    }

    @PatchMapping("/update/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductRequestDto productRequestDto){
        return productService.updateProductDetails(productId,productRequestDto);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product>findAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir){
        return productService.findAllProduct(page,size,sortBy,sortDir);
    }

    @PostMapping("upload-file")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPorudctsViaFile(@RequestParam("file")MultipartFile file){

        productService.saveProductsFromFile(file);
        return "File uploaded and data saved successfully!";
    }

    @GetMapping("exits/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isExistById(@PathVariable("productId") Long productId){
        return productService.isExists(productId);
    }
}
