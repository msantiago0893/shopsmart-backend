package com.app.shopsmart.service;

import com.app.shopsmart.entity.UserEntity;

import java.util.List;

public interface IUserService {
  public List<UserEntity> findAll();

  public UserEntity findById(Long id);

  public Boolean save(UserEntity cliente);

  public UserEntity edit(UserEntity cliente);

  public void delete(Long id);
}
