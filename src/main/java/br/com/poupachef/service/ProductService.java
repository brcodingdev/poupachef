package br.com.poupachef.service;

import br.com.poupachef.dto.request.ProductRequest;
import br.com.poupachef.dto.response.ProductResponse;
import br.com.poupachef.entity.Inventory;
import br.com.poupachef.entity.Product;
import br.com.poupachef.entity.Supplier;
import br.com.poupachef.exceptions.CustomException;
import br.com.poupachef.mapper.ProductMapper;
import br.com.poupachef.repository.ProductRepository;
import br.com.poupachef.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public Page<ProductResponse> getAll(String name, Pageable pageable) {
        Page<Product> productsPage = productRepository.findProductsByNameContains(name, pageable);
        List<Product> products = productsPage.getContent();
        List<ProductResponse> productRequests = ProductMapper.INSTANCE.toResponseList(products);
        Long totalProducts = productRepository.countProductsByNameContains(name);
        return new PageImpl<>(productRequests, pageable, totalProducts);
    }

    public void update(ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(productRequest.getId());

        if (!productOptional.isPresent()) {
            throwNotFoundProduct();
        }

        Optional<Supplier> supplierOptional = supplierRepository.findById(productRequest.getSupplierId());

        if (!supplierOptional.isPresent()) {
            throwNotFoundSupplier();
        }

        Product product = productOptional.get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setUpdatedAt(new Date());
        product.setSupplier(supplierOptional.get());
        productRepository.save(product);
    }

    public void create(ProductRequest productRequest) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(productRequest.getSupplierId());

        if (!supplierOptional.isPresent()) {
            throwNotFoundSupplier();
        }

        Product product
                = ProductMapper.INSTANCE.toEntity(productRequest);

        Date now = new Date();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

        Inventory inventory = Inventory.builder()
                .id(product.getId())
                .createdAt(now)
                .updatedAt(now)
                .product(product)
                .qty(0)
                .build();

        product.setInventory(inventory);
        product.setSupplier(supplierOptional.get());

        productRepository.save(product);
    }

    public ProductResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throwNotFoundProduct();
        }

        return ProductMapper.INSTANCE.toResponse(product.get());
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throwNotFoundProduct();
        }
        productRepository.deleteById(id);
    }

    private void throwNotFoundProduct() {
        throw new CustomException(HttpStatus.NOT_FOUND, "product not found");
    }

    private void throwNotFoundSupplier() {
        throw new CustomException(HttpStatus.NOT_FOUND, "supplier not found");
    }
}
