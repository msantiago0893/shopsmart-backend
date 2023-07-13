package com.app.shopsmart.service;

import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.strategy.EmailStrategy;

public interface EmailService {
  public void sendEmail(EmailStrategy emailStrategy, UserEntity user);
  public void generateTemplate(EmailHtml mail);

}
