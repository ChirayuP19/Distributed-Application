package com.chirayu.service;

import com.chirayu.dto.ProductRequestDto;
import com.chirayu.dto.ProductResponseDto;
import com.chirayu.dto.ProductUpdateDto;
import com.chirayu.entity.Category;
import com.chirayu.entity.Product;
import com.chirayu.exception.DuplicateProductFound;
import com.chirayu.exception.ResourceNotFoundException;
import com.chirayu.mapper.ProductMapper;
import com.chirayu.repository.CategoryRepository;
import com.chirayu.repository.ProductRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDto findByProductId(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID:: " + productId));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        String name = requestDto.getName();
        productRepository.findByName(name)
                .ifPresent(product -> {
                    throw new DuplicateProductFound(
                            "Product already exists with name: " + name
                    );
                });
        Category category = categoryRepository.findById(requestDto.getCategoryID())
                .orElseThrow(() -> new ResourceNotFoundException("Category Id " + requestDto.getCategoryID() + " is not valid"));
        Product productEntity = productMapper.toProductEntity(requestDto);
        productEntity.setCategory(category);
        Product saveProduct = productRepository.save(productEntity);
        return productMapper.toProduct(saveProduct);
    }

    @Override
    @Transactional
    public ProductResponseDto updateStockQuantity(ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(productUpdateDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found with ID:: " + productUpdateDto.getProductId()));
        if (productUpdateDto.getStock() == null) {
            throw new IllegalArgumentException("Stock value is required");
        }
        if (productUpdateDto.getStock() <= 0) {
            throw new IllegalArgumentException("Stock value must be positive.");
        }

        int currentStock = product.getStock() == null ? 0 : product.getStock();
        int newStock = currentStock + productUpdateDto.getStock();

        product.setStock(product.getStock() + productUpdateDto.getStock());
        product.setUpdateAt(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);
        return productMapper.toProduct(updatedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProductDetails(Long productId, ProductRequestDto requestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with ID: " + productId));

        if (requestDto.getName() != null) {
            product.setName(requestDto.getName());
        }

        if (requestDto.getDescription() != null) {
            product.setDescription(requestDto.getDescription());
        }

        if (requestDto.getPrice() != null) {
            product.setPrice(requestDto.getPrice());
        }

        if (requestDto.getStock() != null) {
            int currentStock = product.getStock() == null ? 0 : product.getStock();
            int newStock = currentStock + requestDto.getStock();

            if (newStock < 0) {
                throw new IllegalArgumentException("Stock cannot be negative");
            }

            product.setStock(newStock);
        }

        product.setUpdateAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return productMapper.toProduct(savedProduct);
    }

    @Override
    public Page<Product> findAllProduct(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    @Override
    public void saveProductsFromFile(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())){

            CSVReader csvReader = new CSVReader(reader);
            List<String[]> records = csvReader.readAll();
            List<Product> products = new ArrayList<>();

            for (int i=1; i< records.size();i++ ){
                String[] record = records.get(i);

                Product product = new Product();
                product.setName(record[0]);
                product.setDescription(record[1]);
                product.setPrice(new BigDecimal(record[2]));
                product.setStock(Integer.parseInt(record[3]));
                String categoryName = record[4];
                Category category = categoryRepository.findByName(categoryName)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryName));
                product.setCategory(category);

                products.add(product);
            }
            productRepository.saveAll(products);
        }catch (Exception e){
            throw new RuntimeException("Failed to process file", e);
        }
    }

    @Override
    public Boolean isExists(Long productId) {
        return productRepository.findById(productId).isPresent();
    }

    @Override
    public BigDecimal getProductPriceById(Long productId) {
        return productRepository.findProductPriceByProductId(productId);
    }

    @Override
    public List<ProductResponseDto> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllByIdIn(productIds)
                .stream().map(productMapper::toDto)
                .toList();
    }

    @Override
    public void reduceStockQuantityById(Long productId, Integer quantity) {
        int updatedRow = productRepository.reduceStockIfAvailable(productId, quantity);
        if (updatedRow == 0) {
            throw new RuntimeException("Out of stock or product not found with ID: " + productId);
        }
    }
}
