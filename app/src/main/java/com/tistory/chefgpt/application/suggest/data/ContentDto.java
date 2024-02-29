package com.tistory.chefgpt.application.suggest.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.chefgpt.application.suggest.data.res.ResponseChatDto;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContentDto {
  private final String title;
  private final String description;
  private final Contents contents;

  @JsonCreator
  ContentDto(
      @JsonProperty("title") final String title,
      @JsonProperty("description") final String description,
      @JsonProperty("contents") final Contents contents) {
    this.title = title;
    this.description = description;
    this.contents = contents;
  }

  public static ContentDto of(ResponseChatDto res) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    return om.readValue(
              res.choices().stream().findAny().get().message().content(),
                ContentDto.class
            );
  }

  record Contents(List<String> ingredients, List<String> recipe) {}
}
