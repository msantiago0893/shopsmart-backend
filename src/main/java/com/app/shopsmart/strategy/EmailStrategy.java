package com.app.shopsmart.strategy;

import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;

public interface EmailStrategy {

  public EmailHtml send(UserEntity user);
}
