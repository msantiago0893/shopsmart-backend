package com.app.shopsmart.controller;

import com.app.shopsmart.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.shopsmart.service.IProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IProductoService productService;

  @GetMapping
  public List<ProductEntity> getAllProducts() {
    log.info("Fetching all products");
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
    log.info("Fetching product with id: {}", id);

    ProductEntity product = productService.findById(id);

    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<ProductEntity> create(@RequestBody ProductEntity product) {
    log.info("Creating product: {}", product);

    ProductEntity createdProduct = productService.save(product);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity product, @PathVariable Long id) {
    log.info("update product: {}", id );

    ProductEntity productFound = productService.findById(id);

    productFound.setName(product.getName());
    productFound.setDescription(product.getDescription());
    productFound.setPrice(product.getPrice());
    productFound.setImage(product.getImage());

    return ResponseEntity.ok(productFound);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    log.info("Deleting product with id: {}", id);

    ProductEntity productFound = productService.findById(id);

    if (productFound == null) {
      return ResponseEntity.notFound().build();
    }

    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
