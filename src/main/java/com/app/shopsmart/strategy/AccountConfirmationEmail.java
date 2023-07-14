package com.app.shopsmart.strategy;

import com.app.shopsmart.constants.Messages;
import com.app.shopsmart.constants.Urls;
import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;

public class AccountConfirmationEmail implements EmailStrategy {

  @Override
  public EmailHtml send(UserEntity user) {
    EmailHtml mail = new EmailHtml();
      mail.setNameTemplate(Messages.CREATE_ACCOUNT);
      mail.setUser(user.getName());
      mail.setTo(user.getEmail());
      mail.setSubject(Messages.ACCOUNT_CONFIRMATION);
      mail.setUrl(Urls.CONFIRM_ACCOUNT + user.getToken());

    return mail;
  }
}
