package com.app.shopsmart.service;

import com.app.shopsmart.entity.UserEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IAuthService {
  public Boolean forgotPassword(String token) throws MessagingException, UnsupportedEncodingException;
  public UserEntity findByToken(String token);
  public UserEntity findByEmail(String email);
  public UserEntity save(UserEntity user);
}
