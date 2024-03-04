package com.tistory.chefgpt.application.image.data.req;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageResponseType {
  URL("url"),
  B64JSON("b64_json");

  private String type;

  ImageResponseType(String type) {
    this.type = type;
  }

  @JsonValue
  public String getType() {
    return this.type;
  }
}
