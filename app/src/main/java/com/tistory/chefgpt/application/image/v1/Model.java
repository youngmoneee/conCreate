package com.tistory.chefgpt.application.image.v1;

public enum Model {
  DallE2("dall-e-2"),
  DallE3("dall-e-3");

  private final String model;

  Model(String model) {
    this.model = model;
  }

  public String getModel() {
    return this.model;
  }
}
