package com.tistory.chefgpt.suggest;

import com.tistory.chefgpt.application.suggest.Suggestable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestSuggestService {
  private final Suggestable suggestService;

  @Autowired
  TestSuggestService(Suggestable service) {
    this.suggestService = service;
  }

  @Test
  @DisplayName("DI 테스트")
  public void init() {
    assertThat(this.suggestService).isNotNull();
    assertThat(this.suggestService).isInstanceOf(Suggestable.class);
  }

  @Test
  @DisplayName("요청 테스트")
  public void response() {
    System.out.println(this.suggestService.suggest().block());
  }
}
