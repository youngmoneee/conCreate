package com.tistory.chefgpt.application.suggest.data;

public enum Provider {
  //  모델명 수정
  OPENAI("gpt-3.5-turbo-0125"),
  BARD("bard");

  private final String provider;

  Provider(String provider) {
    this.provider = provider;
  }

  public String getProvider() {
    return provider;
  }
}
