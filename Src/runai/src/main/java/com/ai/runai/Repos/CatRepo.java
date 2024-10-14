package com.ai.runai.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.runai.Models.Category;

public interface CatRepo extends JpaRepository<Category, Long> {
    public Optional<Category> findByName(String name);
}
