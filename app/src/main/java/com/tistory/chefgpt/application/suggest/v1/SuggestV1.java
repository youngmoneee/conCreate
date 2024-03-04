package com.tistory.chefgpt.application.suggest.v1;

import com.tistory.chefgpt.application.suggest.Suggestable;
import com.tistory.chefgpt.application.suggest.data.Provider;
import com.tistory.chefgpt.application.suggest.data.res.ResponseChatDto;
import com.tistory.chefgpt.util.api.WebClientFactory;
import com.tistory.chefgpt.util.api.WebClientFactory.App;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SuggestV1 implements Suggestable {
  private final WebClientFactory webClientFactory;
  private final SuggestRequestFactory requestFactory;

  @Override
  public Mono<ResponseChatDto> suggest() {
    return this.webClientFactory.create(App.SUGGEST)
        .post()
        .header("Content-Type", "application/json")
        .bodyValue(this.requestFactory.create(Provider.OPENAI))
        .retrieve()
        .bodyToMono(ResponseChatDto.class);
  }
}
