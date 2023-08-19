package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    private long exintindId;
    private long nonExistindId;
    private long countTotalProducts;
    @BeforeEach
    void setUp() throws Exception{
        exintindId = 1L;
        nonExistindId = 1000L;
        countTotalProducts = 25L;

    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull(){

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());

    }


    @Test
    public void deleteShouldDeleteObjWhenIdExists(){

        repository.deleteById(exintindId);
        Optional<Product> result = repository.findById(exintindId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistindId);
        });
    }
}
