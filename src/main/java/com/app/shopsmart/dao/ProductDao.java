package com.app.shopsmart.dao;

import com.app.shopsmart.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<ProductEntity, Long> {

}
