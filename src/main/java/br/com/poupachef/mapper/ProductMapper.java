package br.com.poupachef.mapper;

import br.com.poupachef.dto.request.ProductRequest;
import br.com.poupachef.dto.response.ProductResponse;
import br.com.poupachef.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "supplier.id", source = "supplier.id")
    @Mapping(target = "supplier.name", source = "supplier.name")
    @Mapping(target = "qty", source = "inventory.qty")
    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest productRequest);

    @Mapping(target = "supplier.id", source = "supplier.id")
    @Mapping(target = "supplier.name", source = "supplier.name")
    @Mapping(target = "qty", source = "inventory.qty")
    List<ProductResponse> toResponseList(List<Product> products);

}
