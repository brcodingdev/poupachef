package br.com.poupachef.service;

import br.com.poupachef.dto.response.ProductResponse;
import br.com.poupachef.entity.Product;
import br.com.poupachef.exceptions.CustomException;
import br.com.poupachef.repository.ProductRepository;
import br.com.poupachef.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    private ProductService productService;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void before() {
        productService = new ProductService(productRepository, supplierRepository);
    }

    @Test
    void shouldReturn_whenThereAreResults() {
        final String name = "013";
        PageRequest pageRequest = PageRequest.of(0, 10);
        PageRequest pageResult = PageRequest.of(0, 2);
        List<Product> products = generateProductList();
        Page<Product> productPage = new PageImpl<>(products, pageResult, 2);
        when(productRepository.findProductsByNameContains(name, pageRequest))
                .thenReturn(productPage);
        when(productRepository.countProductsByNameContains(name)).thenReturn(2L);

        Page<ProductResponse> productsReturned = productService.getAll(name, pageRequest);

        assertEquals(2L, productsReturned.getTotalElements());
        assertEquals(2, productsReturned.getContent().size());
        assertEquals(1L, productsReturned.getContent().get(0).getId().longValue());
    }

    @Test
    void shouldReturn_whenProductAlreadyCreated() {
        final long ID = 1L;
        Product product = generateProductList().get(0);
        Optional<Product> productOptional = Optional.of(product);
        when(productRepository.findById(ID)).thenReturn(productOptional);
        ProductResponse productRequest = productService.getById(1L);
        assertNotNull(productRequest);
        assertEquals(ID, productRequest.getId().longValue());
    }

    @Test
    void shouldNotReturn_whenProductWasNotCreated() {
        final long ID = 1L;
        Optional<Product> productOptional = Optional.of(Product.builder().build());
        when(productRepository.findById(ID)).thenReturn(productOptional);
        assertThrows(CustomException.class, () -> productService.getById(2L));
    }

    @Test
    void shouldRaiseError_whenDelete() {
        when(productRepository.existsById(1L)).thenReturn(true);
        assertThrows(CustomException.class, () -> productService.delete(2L));
    }

    private List<Product> generateProductList() {
        return Arrays.asList(Product.builder().id(1L).build(),
                Product.builder().id(2L).build());
    }
}
