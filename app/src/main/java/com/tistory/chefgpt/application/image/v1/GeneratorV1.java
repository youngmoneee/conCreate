package com.tistory.chefgpt.application.image.v1;

import com.tistory.chefgpt.application.image.Generatable;
import com.tistory.chefgpt.application.image.data.req.RequestImageDto;
import com.tistory.chefgpt.application.image.data.res.ResponseImageDto;
import com.tistory.chefgpt.util.api.WebClientFactory;
import com.tistory.chefgpt.util.api.WebClientFactory.App;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeneratorV1 implements Generatable {
  private final ImageRequestFactory requestFactory;
  private final WebClientFactory webClientFactory;

  @Override
  public Mono<ResponseImageDto> generate(String menu) {
    return this.webClientFactory.create(App.IMAGE)
        .post()
        .header("Content-Type", "application/json")
        .body(Mono.just(this.requestFactory.create(menu)), RequestImageDto.class)
        .retrieve()
        .bodyToMono(ResponseImageDto.class);
  }

}
