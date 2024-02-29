package com.tistory.chefgpt.application.suggest.data.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tistory.chefgpt.application.suggest.data.Message;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RequestChatDto {
  private final String  model;
  @JsonProperty("response_format")
  private final CreateChatTypeDto responseType;
  private final List<Message> messages;

  public record CreateChatTypeDto(String type) {}

  public boolean addMessage(Message msg) {
    return this.messages.add(msg);
  }

  public boolean addMessage(String role, String content) {
    return this.messages.add(new Message(role, content));
  }
}