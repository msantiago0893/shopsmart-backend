package com.app.shopsmart.serviceImpl;

import com.app.shopsmart.dao.ProductDao;
import com.app.shopsmart.entity.ProductEntity;
import com.app.shopsmart.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductoService {
  @Autowired
  private ProductDao productDao;

  @Override
  public List<ProductEntity> findAll() {
    return (List<ProductEntity>) productDao.findAll();
  }

  @Override
  public ProductEntity findById(Long id) {
    return null;
  }

  @Override
  public ProductEntity save(ProductEntity book) {
    return productDao.save(book);
  }

  @Override
  public void delete(Long id) {

  }
}
