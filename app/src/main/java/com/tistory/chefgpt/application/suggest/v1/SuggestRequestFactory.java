package com.tistory.chefgpt.application.suggest.v1;

import com.tistory.chefgpt.application.suggest.conf.Prompt;
import com.tistory.chefgpt.application.suggest.data.Provider;
import com.tistory.chefgpt.application.suggest.data.req.RequestChatDto;
import com.tistory.chefgpt.application.suggest.data.req.RequestChatDto.CreateChatTypeDto;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuggestRequestFactory {
  private final Prompt.GptPrompt gptPrompt;

  public RequestChatDto create(final Provider p) {
    return switch (p) {
      case OPENAI -> RequestChatDto.builder()
          .model(p.getProvider())
          .responseType(new CreateChatTypeDto("json_object"))
          .messages(new ArrayList<>(this.gptPrompt.messages()))
          .build();
      case BARD -> null;
    };
  }
}
