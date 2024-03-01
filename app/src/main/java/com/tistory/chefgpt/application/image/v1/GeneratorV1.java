package com.tistory.chefgpt.application.image.v1;

import com.tistory.chefgpt.application.image.Generatable;
import com.tistory.chefgpt.application.image.data.res.ResponseImageDto;
import reactor.core.publisher.Mono;

public class GeneratorV1 implements Generatable {

  @Override
  public Mono<ResponseImageDto> generate() {
    return null;
  }
}
