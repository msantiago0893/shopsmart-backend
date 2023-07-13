package com.app.shopsmart.security;

import com.app.shopsmart.component.AdditionalInfoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authenticationManager;

  @Autowired
  private AdditionalInfoToken infoAdicionalToken;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
      .tokenKeyAccess("permitAll()")
      .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
      .withClient("shopsmart")
      .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
      .authorities("ROLE_CLIENT","ROLE_MANAGER")
      .scopes("read", "write")
      .autoApprove(true)
      .secret(passwordEncoder.encode("12345"))
      .accessTokenValiditySeconds(3600)
      .refreshTokenValiditySeconds(3600);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));

    endpoints
      .authenticationManager(authenticationManager)
      .tokenStore(tokenStorage())
      .accessTokenConverter(accessTokenConverter())
      .tokenEnhancer(tokenEnhancerChain);
  }

  @Bean
  public JwtTokenStore tokenStorage() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
    jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
    return jwtAccessTokenConverter;
  }
}
