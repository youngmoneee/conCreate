package com.tistory.chefgpt.application.image.conf;

import com.tistory.chefgpt.application.image.v1.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class ImageConfig {

  @Bean
  Model defaultModel() {
    return Model.DallE3;
  }

  @Bean
  @Primary
  @Profile({"test", "dev"})
  Model testModel() {
    return Model.DallE2;
  }
}
