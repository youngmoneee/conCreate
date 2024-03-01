package com.tistory.chefgpt.application.image;

import com.tistory.chefgpt.application.image.data.res.ResponseImageDto;
import reactor.core.publisher.Mono;

public interface Generatable {
  Mono<ResponseImageDto> generate();
}
