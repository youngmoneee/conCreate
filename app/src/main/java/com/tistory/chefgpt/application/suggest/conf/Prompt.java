package com.tistory.chefgpt.application.suggest.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.chefgpt.application.suggest.data.Message;
import java.io.IOException;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class Prompt {
  @Bean
  GptPrompt gptPrompt() throws IOException {
    Resource gptPrompt = new ClassPathResource("gpt_prompt.json");
    ObjectMapper om = new ObjectMapper();

    return om.readValue(gptPrompt.getInputStream(), GptPrompt.class);
  }

  public record GptPrompt(List<Message> messages){}
}
