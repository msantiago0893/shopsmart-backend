package com.app.shopsmart.component;

import com.app.shopsmart.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;

import com.app.shopsmart.dto.InfoAdittional;
import com.app.shopsmart.entity.UserEntity;

@Component
public class AdditionalInfoToken implements TokenEnhancer {
  @Autowired
  private IAuthService authService;

  @Override
  public OAuth2AccessToken enhance(
    OAuth2AccessToken accessToken,
    OAuth2Authentication authentication) {

    UserEntity user =  authService.findByEmail(authentication.getName());

    InfoAdittional infoUser = new InfoAdittional();
    infoUser.setName(user.getName());
    infoUser.setFirstame(user.getFirstName());
    infoUser.setSecondname(user.getLastName());
    infoUser.setRole(user.getRole());

    Map<String, Object> info  = new HashMap<>();
    info.put("user", infoUser);

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

    return accessToken;
  }
}
