package com.tistory.chefgpt.application.image.data.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestImageDto {
  private String prompt;
  private final String size;
  private final String model;
  @JsonProperty("response_format")
  private ImageResponseType responseFormat;

  public RequestImageDto b64Response() {
    this.responseFormat = ImageResponseType.B64JSON;
    return this;
  }

  public static RequestImageDto of(String model, String size) {
    return RequestImageDto.builder()
        .model(model)
        .size(size)
        .build();
  }
}