package com.app.shopsmart.serviceImpl;

import com.app.shopsmart.constants.Urls;
import com.app.shopsmart.dao.UserDao;
import com.app.shopsmart.dto.EmailHtml;
import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.service.EmailService;
import com.app.shopsmart.service.IAuthService;
import com.app.shopsmart.service.IUserService;
import com.app.shopsmart.strategy.AccountConfirmationEmail;
import com.app.shopsmart.strategy.ForgotPasswordEmail;
import com.app.shopsmart.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {
  private Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private EmailService emailService;

  @Override
  public Boolean forgotPassword(String email) {
    log.info("forgot password {}", email);

    Optional<UserEntity> userOptional = Optional.ofNullable(userDao.findByEmailIgnoreCase(email));

    if (userOptional.isPresent()) {
      Token token = new Token();
      UserEntity user = userOptional.get();
        user.setToken(token.generateToken());
        user.setTokenCreatedAt(LocalDateTime.now());
        user = userDao.save(user);

      emailService.sendEmail(new ForgotPasswordEmail(), user);

      log.info("account recovery link sent");

      return true;
    }
    return false;
  }

  @Override
  public UserEntity findByToken(String token) {
    log.info("Token received {}", token);
    return userDao.findByToken(token);
  }

  @Override
  public UserEntity findByEmail(String email) {
    log.info("find email {}", email);
    return userDao.findByEmailIgnoreCase(email);
  }

  @Override
  public UserEntity save(UserEntity user) {
    log.info("update user ");
    return userDao.save(user);
  }
}