package com.project.web_shopapp.repositories;

import com.project.web_shopapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String title);
    Page<Product> findAll(Pageable pageable); // phan trang
}
