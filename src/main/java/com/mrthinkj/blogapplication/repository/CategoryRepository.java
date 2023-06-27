package com.mrthinkj.blogapplication.repository;

import com.mrthinkj.blogapplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
