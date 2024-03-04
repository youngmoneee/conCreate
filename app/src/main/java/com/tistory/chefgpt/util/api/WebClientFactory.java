package com.tistory.chefgpt.util.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
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
    log.info("WebClient Created");
    WebClient.Builder res = WebClient.builder().baseUrl(app.getUrl());
    return switch (app) {
      case SUGGEST -> res
          .defaultHeader(
              HttpHeaders.AUTHORIZATION,
              "Bearer " + this.secrets.getOpenAi().getApiKey()
          ).build();
      case IMAGE -> res
          .defaultHeader(
              HttpHeaders.AUTHORIZATION,
              "Bearer " + this.secrets.getOpenAi().getApiKey()
          ).exchangeStrategies(
              ExchangeStrategies.builder()
                  .codecs(c -> c.defaultCodecs().maxInMemorySize(
                      16 * 1024 * 1024  //  16Mb
                  ))
                  .build()
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
