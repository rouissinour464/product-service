
package com.store.productservice.repository;
import com.store.productservice.entity.Product;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> { List<Product> findByNomContainingIgnoreCase(String nom); }
