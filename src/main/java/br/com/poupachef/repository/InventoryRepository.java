package br.com.poupachef.repository;

import br.com.poupachef.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Inventory i SET i.qty = i.qty + :qty, i.updatedAt = CURRENT_TIMESTAMP WHERE i.product.id = :productId")
    void add(@Param("productId") Long productId, @Param("qty") Integer qty);

    @Modifying
    @Transactional
    @Query("UPDATE Inventory i SET i.qty = i.qty - :qty, i.updatedAt = CURRENT_TIMESTAMP WHERE i.product.id = :productId")
    void subtract(@Param("productId") Long productId, @Param("qty") Integer qty);
}
