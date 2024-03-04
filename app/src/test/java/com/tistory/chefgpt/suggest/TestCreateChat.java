package com.tistory.chefgpt.suggest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.chefgpt.application.suggest.data.ContentDto;
import com.tistory.chefgpt.application.suggest.data.req.RequestChatDto;
import com.tistory.chefgpt.application.suggest.data.Provider;
import com.tistory.chefgpt.application.suggest.data.res.ResponseChatDto;
import com.tistory.chefgpt.application.suggest.v1.SuggestRequestFactory;
import com.tistory.chefgpt.util.api.Secrets;
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
@DisplayName("메뉴 추천 테스트")
public class TestCreateChat {
  private final Secrets secrets;
  private final SuggestRequestFactory requestFactory;
  private final WebClientFactory webClientFactory;

  @Autowired
  public TestCreateChat(Secrets v1, SuggestRequestFactory v2, WebClientFactory v3) {
    this.secrets = v1;
    this.requestFactory = v2;
    this.webClientFactory = v3;
  }

  @Test
  @DisplayName("DI 테스트")
  public void init() {
    assertThat(this.secrets).isNotNull();
    assertThat(this.secrets).isInstanceOf(Secrets.class);
    assertThat(this.requestFactory).isNotNull();
    assertThat(this.requestFactory).isInstanceOf(SuggestRequestFactory.class);
  }

  @Test
  @DisplayName("Properties 주입 테스트")
  public void secretInjection() {
    System.out.println(this.secrets.getOpenAi());
    System.out.println(this.secrets.getNaver());
  }

  @Test
  @DisplayName("요청 바디 초기화 테스트")
  public void requestFactory() {
    RequestChatDto req = this.requestFactory.create(Provider.OPENAI);

    assertThat(req.getResponseType().type()).isEqualTo("json_object");
  }

  @Test
  @DisplayName("프롬프트 확인")
  public void GPT_프롬프트_주입() {
    RequestChatDto req = this.requestFactory.create(Provider.OPENAI);

    req.getMessages().forEach(msg -> {
      System.out.println("role : " + msg.role());
      System.out.println("content : " + msg.content());
      System.out.println();
    });
  }

  @Test
  @DisplayName("스레드 안전 테스트")
  public void messages_불변_테스트() {
    RequestChatDto req1 = this.requestFactory.create(Provider.OPENAI);
    req1.addMessage("system", "test");
    RequestChatDto req2 = this.requestFactory.create(Provider.OPENAI);

    assertThat(req1.getMessages()).isNotEqualTo(req2.getMessages());
  }

  @Test
  @DisplayName("요청 성공 여부, Content 객체 생성 테스트")
  public void responseTest() {
    RequestChatDto req = this.requestFactory.create(Provider.OPENAI);
    ObjectMapper om = new ObjectMapper();
    WebClient wc = this.webClientFactory.create(App.SUGGEST);

    ResponseChatDto res = wc.post()
        .header("Content-Type", "application/json")
        .body(Mono.just(req), RequestChatDto.class)
        .retrieve()
        .bodyToMono(ResponseChatDto.class)
        .block();
    System.out.println(res);

    try {
      ContentDto content = ContentDto.of(res);
      System.out.println(om.writeValueAsString(content));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
