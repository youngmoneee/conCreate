package com.tistory.chefgpt.image;

import com.tistory.chefgpt.application.image.data.req.RequestImageDto;
import com.tistory.chefgpt.application.image.data.res.ResponseImageDto;
import com.tistory.chefgpt.application.image.v1.ImageRequestFactory;
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
  private final ImageRequestFactory requestFactory;
  private final WebClientFactory webClientFactory;

  @Autowired
  TestCrateImage(
      WebClientFactory webClientFactory,
      ImageRequestFactory requestFactory
  ) {
    this.requestFactory = requestFactory;
    this.webClientFactory = webClientFactory;
  }

  @Test
  @DisplayName("DI 테스트")
  public void diTest() {
    assertThat(this.requestFactory).isNotNull();
    assertThat(this.requestFactory).isInstanceOf(ImageRequestFactory.class);
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
  @DisplayName("DallE 2 이미지 생성 테스트")
  public void image2() {
    RequestImageDto req = this.requestFactory.create("김치찌개");
    WebClient wc = this.webClientFactory.create(App.IMAGE);

    ResponseImageDto res = wc.post()
        .header("Content-Type", "application/json")
        .body(Mono.just(req), RequestImageDto.class)
        .retrieve()
        .bodyToMono(ResponseImageDto.class)
        .block();
    System.out.println(res);
  }

  @Test
  @DisplayName("DallE 2 이미지 b64 테스트")
  public void b64JsonTest() {
    RequestImageDto req = this.requestFactory.create("김치찌개").b64Response();
    WebClient wc = this.webClientFactory.create(App.IMAGE);

    ResponseImageDto res = wc.post()
        .header("Content-Type", "application/json")
        .body(Mono.just(req), RequestImageDto.class)
        .retrieve()
        .bodyToMono(ResponseImageDto.class)
        .block();
    System.out.println(res.data().get(0).b64Json());
  }
}