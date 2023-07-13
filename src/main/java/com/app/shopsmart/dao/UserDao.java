package com.app.shopsmart.dao;

import com.app.shopsmart.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

  public UserEntity findByName(String name);
  public UserEntity findByEmailIgnoreCase(String email);
  public UserEntity findByToken(String token);
}