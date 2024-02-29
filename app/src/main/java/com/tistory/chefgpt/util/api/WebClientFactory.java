package com.tistory.chefgpt.util.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WebClientFactory {
  public enum App {
    SUGGEST("https://api.openai.com/v1/chat/completions"),
    IMAGE("https://api.openai.com//v1/images/generations"),
    POSTING("https://티스토리"),
    PAPAGO("https://openapi.naver.com/v1/papago/n2mt");

    private final String url;

    public String getUrl() {
      return this.url;
    }

    App(final String url) {
      this.url = url;
    }
  }

  private final Secrets secrets;

  public WebClient create(final App app) {
    WebClient.Builder res = WebClient.builder().baseUrl(app.getUrl());
    return switch (app) {
      case SUGGEST, IMAGE -> res
          .defaultHeader(
              HttpHeaders.AUTHORIZATION,
              "Bearer " + this.secrets.getOpenAi().getApiKey()
          ).build();
      case POSTING -> res
          .defaultHeader(
              HttpHeaders.AUTHORIZATION,
              "티스토리 토큰" // TODO : Tistory
          ).build();
      case PAPAGO -> res
          .defaultHeader(
              HttpHeaders.AUTHORIZATION,
                  "Bearer " + this.secrets.getNaver().getApiKey()
              ).build();
    };
  }
}
