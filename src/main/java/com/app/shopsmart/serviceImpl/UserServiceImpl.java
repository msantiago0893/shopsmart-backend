package com.app.shopsmart.serviceImpl;
import com.app.shopsmart.strategy.AccountConfirmationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.shopsmart.dao.UserDao;
import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.enums.Numbers;
import com.app.shopsmart.enums.Roles;
import com.app.shopsmart.service.EmailService;
import com.app.shopsmart.service.IUserService;
import com.app.shopsmart.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Lazy
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private UserDao userDao;
  @Autowired
  private EmailService emailService;

  @Override
  @Transactional(readOnly=true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity usuario = userDao.findByEmailIgnoreCase(username);

    if(usuario == null) {
      logger.warn("user {} was not found ", username);

      throw new UsernameNotFoundException("user does not exist");
    }

    List<GrantedAuthority> authorities = Arrays
      .asList(usuario.getRole())
      .stream()
      .map(role -> new SimpleGrantedAuthority(role))
      .collect(Collectors.toList());

    return new User(
      usuario.getEmail(),
      usuario.getPassword(),
      usuario.getEnabled(),
      true,
      true,
      true,
      authorities
    );
  }

  @Override
  public List<UserEntity> findAll() {
    logger.info("find all users");
    return (List<UserEntity>) userDao.findAll();
  }

  @Override
  public UserEntity findById(Long id) {
    logger.info("find user {}", id);
    return userDao.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Boolean save(UserEntity user) {
    logger.info("save user");

    Token token = new Token();

    user.setToken(token.generateToken());
    user.setPassword(passwordEncoder().encode(user.getPassword()));
    user.setRole(
      (user.getRole().length() > Numbers.ZERO.ordinal()) ? user.getRole(): Roles.CUSTOMER.toString()
    );

    UserEntity userCreated = userDao.save(user);

    if (userCreated != null ) {
      logger.info("User created");

      this.emailService.sendEmail(new AccountConfirmationEmail(),userCreated);

      return true;
    }
    return false;
  }

  @Override
  public void delete(Long id) {
    userDao.deleteById(id);
  }

  @Override
  public UserEntity edit(UserEntity usuario) {
    return userDao.save(usuario);
  }

}