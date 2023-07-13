package com.app.shopsmart.security;

public class JwtConfig {
  public static final String KEY_SECRET="shop.smart.$Antiago.2023";
  public static final String RSA_PRIVATE="-----BEGIN RSA PRIVATE KEY-----\n" +
          "MIIEowIBAAKCAQEAqbamdZn2BNOd3V6wx3ALb0vg/9vLHkyxZ5RB7AajcyLVIOn+\n" +
          "U7+NQr9itzQXkBHqb6IszmrB6S3TvEpj4mcWpqDNczpuL0WfCkUyAnZ+DnadSNo7\n" +
          "FOuYoEIZTD3k0KOphu1rkQIXxUYdJPxRp7CTOClUZSgemwhciRZB4qLOPypQH6+g\n" +
          "OsgIQTdG/wQbO0TwqfCk0yhB4W7xGVlE9IVCai/qkfO2jgkYq/OLrH3/hXio1gv3\n" +
          "qbvkDYPpFSzHy49BAuBsEKFt467eF99v1wLbaY8lLSOJDOrw7/OPtLZ+THPnoxmN\n" +
          "CrEWEzxXbIITnvhqTxHq0CDy3mCUosooYeaFuQIDAQABAoIBAGZH3vBXNo7YHncQ\n" +
          "n0fC/hE4UMTCaRq8q9o53rTpl05Het0YSJ+efPryLwMskNrSAVEAEzLf4jeu18HN\n" +
          "r37+FAeeN9OvLkKtGdlttGMJmJUM5WPHqf3Eszo6O9bYInOffwcDRWWiNkM9K0lk\n" +
          "C64dW8xoFCXW1QMoOeSgllqJNfWYhfOWsUt1CpZF/GXZKrUVXxIx4YaZ6/tKjXZp\n" +
          "uWjvhWMfkKyW+BIq8qnMJpmtJWDKWkF26qNIxpQjMA1a5c5gjzjtagAUeY/zFlfd\n" +
          "/ua5+c/N1FkkbQdgPB14tUYlTTG3V2kZBcCr5eI4rZ10vZwIs9jqwNA1pU9gPB/O\n" +
          "T/6b80UCgYEA3atDEthf3e/f+ZAMwSJ2awqokP5Tp+i3ImgoQRPFF7Jvqxwhuvp+\n" +
          "uq8pn/Kfj0omr8BNXUMIyN8i96TWSwTEXOyDYvUq6AYoRKqY+HgCidGGLF3kzG5d\n" +
          "EaMCmuDHLFdCpDVneI6tbAHTF1LcehqURI4WFNDqJ/EmPND30iJGcpMCgYEAw/90\n" +
          "YfhmUwNYabadl6tpT/BOoc/lKG1bDwPMCnpycEXqprWoraRO2uTpfwGj51EbNeBM\n" +
          "BBSvvJc0oLpEJnKVHCstiiGjA6fXfJJNSU1nnhISNqtRzeTTMFPnTPHjfTwTwj4m\n" +
          "pp5RE2lAVxbWjC3/SHb7Ag72C6nu8mqMifO22gMCgYBH5Sw0CInPaeWzA82fNpZB\n" +
          "0Ysqu9JWO1xqhG3J7aeXMACX9t/ZzTUdR4KwpfiqeNpjVnPBM+S9geHNgGEgo8Ms\n" +
          "gGl8xxfLeXPGEa7bp9Dq8lIaQbnbtE/rUHuf0BfJgePTbp7Isz38MftNLUrlL4m3\n" +
          "Un5G8huAKO7F+uDl/sfqkQKBgGbod8kAzWkEtqU6tt/tpivMttyK9X0WfdsSWU2f\n" +
          "IPsGGsBRXTFetfCEhHyk9dP8Z9cP35m86tCQa5eAdUvimZvip9SA+MBwndnQTzgP\n" +
          "pjn+rwV5Skhavu/+/dEOqC0XICC61Txin5sbhiaz3Q5ZEFKR9hUMme9h2A38F7JO\n" +
          "/lDVAoGBAL3wDVFwi/XTBmooi08lKF2ZkWnmiqMXtr+wFndYPQfq/r90A7NzfP4x\n" +
          "WFU1NG2nFpSm9lF3HLo2N1CGA8tZUa718/m72g1F4yJFZ/owstbKfqKO+ajqMS/I\n" +
          "w5VWlKJScWWP5/hTzz80wu46+fgMJOrVnzM8jAzB1uhv5wyxCj0M\n" +
          "-----END RSA PRIVATE KEY-----";
  public static final String RSA_PUBLIC="-----BEGIN PUBLIC KEY-----\n" +
          "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqbamdZn2BNOd3V6wx3AL\n" +
          "b0vg/9vLHkyxZ5RB7AajcyLVIOn+U7+NQr9itzQXkBHqb6IszmrB6S3TvEpj4mcW\n" +
          "pqDNczpuL0WfCkUyAnZ+DnadSNo7FOuYoEIZTD3k0KOphu1rkQIXxUYdJPxRp7CT\n" +
          "OClUZSgemwhciRZB4qLOPypQH6+gOsgIQTdG/wQbO0TwqfCk0yhB4W7xGVlE9IVC\n" +
          "ai/qkfO2jgkYq/OLrH3/hXio1gv3qbvkDYPpFSzHy49BAuBsEKFt467eF99v1wLb\n" +
          "aY8lLSOJDOrw7/OPtLZ+THPnoxmNCrEWEzxXbIITnvhqTxHq0CDy3mCUosooYeaF\n" +
          "uQIDAQAB\n" +
          "-----END PUBLIC KEY-----";
}
