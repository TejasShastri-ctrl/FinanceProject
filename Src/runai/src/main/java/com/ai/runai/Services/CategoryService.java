package com.ai.runai.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.ai.runai.Models.Category;
import com.ai.runai.Repos.CatRepo;

@Service
public class CategoryService {
    
    @Autowired
    public CatRepo catRepo;

    public Category createCat(String name) throws Exception {
        Optional<Category> catOpt = catRepo.findByName(name);
        if(catOpt.isPresent()) {
            throw new Exception("Category already exists");
        }
        Category scat = catRepo.save(new Category(name));
        return scat;
    }
    

    public List<Category> getAllCats() throws Exception {
        List<Category> allcats = catRepo.findAll();
        if(allcats == null) {
            throw new Exception("Cats not fetched");
        } //! embarrassing
        return allcats;
    }

    public void deleteCatByName(String name) throws Exception {
        Optional<Category> catOpt = catRepo.findByName(name);
        if (!catOpt.isPresent()) {
            throw new Exception("Category not found");
        }
        Category cat = catOpt.get();
        catRepo.delete(cat);
    }
    
}
