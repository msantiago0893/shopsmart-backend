package com.app.shopsmart.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import com.app.shopsmart.constants.Urls;
import com.app.shopsmart.entity.UserEntity;
import com.app.shopsmart.service.EmailService;
import com.app.shopsmart.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT})
@RestController
public class AuthController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IAuthService authService;

  @Autowired
  private EmailService emailService;


  @GetMapping("/confirm-account/{confirmationToken}")
  public void show(HttpServletResponse response, @PathVariable("confirmationToken") String confirmationToken) throws IOException {
    log.info("Confirm account password");

    UserEntity userFound = authService.findByToken(confirmationToken);

    if(userFound != null) {
      UserEntity user = authService.findByEmail(userFound.getEmail());
      user.setEnabled(true);

      authService.save(user);

      response.sendRedirect(Urls.REDIRECT_SHOPSMART);
    }
    else
    {
      log.info("Token invalid");
    }
  }

  @PostMapping("/forgot-password")
  public Boolean forgotPassword(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
    log.info("Forgot password");

    return authService.forgotPassword(email);
  }


}
