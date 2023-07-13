package com.app.shopsmart.strategy;

import com.app.shopsmart.constants.Messages;
import com.app.shopsmart.constants.Urls;
import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;

public class ForgotPasswordEmail implements EmailStrategy {

  @Override
  public EmailHtml send(UserEntity user) {
    EmailHtml mail = new EmailHtml();
      mail.setNameTemplate(Messages.FORGOT_PASSWORD);
      mail.setUser(user.getName());
      mail.setTo(user.getEmail());
      mail.setSubject(Messages.ACCOUNT_RECOVERY);
      mail.setUrl(Urls.RESET_PASSWORD + user.getToken());

    return mail;
  }
}
