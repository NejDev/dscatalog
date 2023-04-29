package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.DTO.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.exceptions.DatabaseException;
import com.devsuperior.dscatalog.exceptions.ResourseNotFoundException;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {
    

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
        }


    @Transactional(readOnly = true)
    public ProductDTO findbyId(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourseNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());

    }
@Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        //entity.setName(dto.getName());

        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
@Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getOne(id);
          //  entity.setName(dto.getName());
           entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){

            throw new ResourseNotFoundException("id not found" + id);

        }

}

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourseNotFoundException("id not found " + id);
        }
        catch (DataIntegrityViolationException e){
        throw new DatabaseException("integrity violation");
        }
    }
}





