package com.app.shopsmart.service;

import com.app.shopsmart.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {
  public List<ProductEntity> findAll();

  public ProductEntity findById(Long id);

  public ProductEntity save(ProductEntity book);

  public void delete(Long id);

}
