package com.project.web_shopapp.repositories;

import com.project.web_shopapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
