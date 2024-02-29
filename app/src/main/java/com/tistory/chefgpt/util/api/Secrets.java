package com.tistory.chefgpt.util.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "secret")
public class Secrets {
  private OpenAi openAi;
  private Naver naver;

  @Data
  public static class OpenAi {
    private String apiKey;
    private String organization;
  }

  @Data
  public static class Naver {
    private String apiKey;
    private String secret;
  }
}
