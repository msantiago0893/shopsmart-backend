package com.app.shopsmart.strategy;

import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;

public class EmailContext {
  private EmailStrategy strategy;

  public void setStrategy(EmailStrategy strategy) {
    this.strategy = strategy;
  }

  public EmailHtml sendEmail(UserEntity user) {
    return strategy.send(user);
  }
}
