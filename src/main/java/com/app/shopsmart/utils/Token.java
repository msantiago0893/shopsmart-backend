package com.app.shopsmart.utils;

import java.util.UUID;

public class Token {
  public String generateToken() {
    StringBuilder token = new StringBuilder();

    return token
            .append(UUID.randomUUID().toString())
            .append(UUID.randomUUID().toString())
            .toString();
  }

}
