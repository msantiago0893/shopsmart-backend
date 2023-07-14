package com.app.shopsmart.serviceImpl;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.app.shopsmart.constants.Messages;
import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.service.EmailService;
import com.app.shopsmart.strategy.AccountConfirmationEmail;
import com.app.shopsmart.strategy.EmailContext;
import com.app.shopsmart.strategy.EmailStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {
  private final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;

  @Autowired
  public EmailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @Value("${spring.mail.username}")
  private String sender;

  public void sendEmail(EmailStrategy emailStrategy, UserEntity user) {
    log.info("send email");

    EmailContext context = new EmailContext();
    context.setStrategy(emailStrategy);

    EmailHtml mail = context.sendEmail(user);

    generateTemplate(mail);
  }


  public void generateTemplate(EmailHtml mail) {
    log.info("Generating template");

    try {
      Context context = new Context();
      context.setVariable("mail", mail);

      String process = templateEngine.process(mail.getNameTemplate(), context);
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

      helper.setFrom(sender, Messages.TEAM_SHOPSMART);
      helper.setTo(mail.getTo());
      helper.setSubject(mail.getSubject());
      helper.setText(process, true);

      javaMailSender.send(mimeMessage);
      log.info("Confirmation email sent successfully");
    } catch (MessagingException | UnsupportedEncodingException e) {
      log.error("Failed to generate email: {}", e.getMessage());
    }
  }
}