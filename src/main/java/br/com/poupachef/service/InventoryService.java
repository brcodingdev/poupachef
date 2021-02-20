package br.com.poupachef.service;

import br.com.poupachef.dto.request.InventoryRequest;
import br.com.poupachef.exceptions.CustomException;
import br.com.poupachef.repository.InventoryRepository;
import br.com.poupachef.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public void add(InventoryRequest inventoryRequest) {
        validateProductId(inventoryRequest.getProductId());
        inventoryRepository.add(inventoryRequest.getProductId(),
                inventoryRequest.getQty());
    }

    public void subtract(InventoryRequest inventoryRequest) {
        validateProductId(inventoryRequest.getProductId());
        try {
            inventoryRepository.subtract(inventoryRequest.getProductId(),
                    inventoryRequest.getQty());
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "inventory is already empty");
        }
    }

    private void validateProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throwNotFoundProduct();
        }
    }

    private void throwNotFoundProduct() {
        throw new CustomException(HttpStatus.NOT_FOUND, "product not found");
    }

}
