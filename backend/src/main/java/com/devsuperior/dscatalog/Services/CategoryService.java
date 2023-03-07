package com.devsuperior.dscatalog.Services;

import com.devsuperior.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devsuperior.dscatalog.Entities.Category;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
private CategoryRepository repository;
public List<Category> findAll(){
    return repository.findAll();
}




}
