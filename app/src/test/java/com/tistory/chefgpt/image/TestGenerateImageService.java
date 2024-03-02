package com.tistory.chefgpt.image;

import com.tistory.chefgpt.application.image.Generatable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Generatable")
public class TestGenerateImageService {
  private final Generatable service;

  @Autowired
  TestGenerateImageService(Generatable service) {
    this.service = service;
  }

  @Test
  @DisplayName("DI 테스트")
  public void diTest() {
    assertThat(this.service).isNotNull();
    assertThat(this.service).isInstanceOf(Generatable.class);
  }
}
