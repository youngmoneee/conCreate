package com.tistory.chefgpt.application.suggest;

import com.tistory.chefgpt.application.suggest.data.res.ResponseChatDto;
import reactor.core.publisher.Mono;

public interface Suggestable {
  Mono<ResponseChatDto> suggest();
}
