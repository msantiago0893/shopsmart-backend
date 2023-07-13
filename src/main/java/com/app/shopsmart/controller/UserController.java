package com.app.shopsmart.controller;

import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IUserService userService;

  @GetMapping
  public List<UserEntity> getAllProducts() {
    log.info("Fetching all products");
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getProductById(@PathVariable Long id) {
    log.info("Fetching product with id: {}", id);

    UserEntity product = userService.findById(id);

    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Boolean> create(@RequestBody UserEntity user) {
    log.info("Creating product: {}", user);

    Boolean hasCreated = userService.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(hasCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserEntity> update(@RequestBody UserEntity user, @PathVariable Long id) {
    log.info("update product: {}", id );

    UserEntity userFound = userService.findById(id);

    userFound.setName(user.getName());
    userFound.setFirstName(user.getFirstName());
    userFound.setLastName(user.getLastName());
    userFound.setGender(user.getGender());
    userFound.setBirthdate(user.getBirthdate());
    userFound.setEmail(user.getEmail());
    userFound.setEnabled(user.getEnabled());
    userFound.setRole(user.getRole());
    userFound.setPassword(user.getPassword());
    userFound.setAddress(user.getAddress());

    userService.save(userFound);

    return ResponseEntity.ok(userFound);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    log.info("Deleting product with id: {}", id);

    UserEntity userFound = userService.findById(id);

    if (userFound == null) {
      return ResponseEntity.notFound().build();
    }

    userService.delete(id);

    return ResponseEntity.noContent().build();
  }

}
