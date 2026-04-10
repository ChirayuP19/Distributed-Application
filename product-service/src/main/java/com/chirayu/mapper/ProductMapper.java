package com.chirayu.mapper;

import com.chirayu.dto.ProductRequestDto;
import com.chirayu.dto.ProductResponseDto;
import com.chirayu.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);
    Product toEntity(ProductResponseDto productDto);
    ProductRequestDto toRequestDto(Product product);
    Product toProductEntity(ProductRequestDto productRequestDto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "createAt", source = "createAt")
    @Mapping(target = "updateAt", source = "updateAt")
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponseDto toProduct(Product product);

}
