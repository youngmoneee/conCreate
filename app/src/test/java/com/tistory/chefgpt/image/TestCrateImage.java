package com.tistory.chefgpt.image;

import com.tistory.chefgpt.application.image.data.req.RequestImageDto;
import com.tistory.chefgpt.application.image.data.res.ResponseImageDto;
import com.tistory.chefgpt.application.image.v1.RequestFactory;
import com.tistory.chefgpt.util.api.WebClientFactory;
import com.tistory.chefgpt.util.api.WebClientFactory.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestCrateImage {
  private final RequestFactory requestFactory;
  private final WebClientFactory webClientFactory;

  @Autowired
  TestCrateImage(
      WebClientFactory webClientFactory,
      RequestFactory requestFactory
  ) {
    this.requestFactory = requestFactory;
    this.webClientFactory = webClientFactory;
  }

  @Test
  @DisplayName("DI 테스트")
  public void diTest() {
    assertThat(this.requestFactory).isNotNull();
    assertThat(this.requestFactory).isInstanceOf(RequestFactory.class);
  }

  @Test
  @DisplayName("DallE 2 테스트")
  public void value() {
    RequestImageDto dall2Dto = this.requestFactory.create("김치찌개");

    assertThat(dall2Dto.getModel()).isEqualTo("dall-e-2");
    assertThat(dall2Dto.getSize()).isEqualTo("512x512");
    System.out.println(dall2Dto.getPrompt());
  }

  @Test
  @DisplayName("Dall2 이미지 생성 테스트")
  public void image2() {
    RequestImageDto req = this.requestFactory.create("김치찌개");
    WebClient wc = this.webClientFactory.create(App.IMAGE);

    ResponseImageDto res = wc.post().header("Content-Type", "application/json")
        .body(Mono.just(req), RequestImageDto.class)
        .retrieve()
        .bodyToMono(ResponseImageDto.class)
        .block();
    System.out.println(res);
  }
}