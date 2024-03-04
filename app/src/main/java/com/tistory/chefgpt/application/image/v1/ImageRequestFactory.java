package com.tistory.chefgpt.application.image.v1;

import com.tistory.chefgpt.application.image.data.req.ImageResponseType;
import com.tistory.chefgpt.application.image.data.req.RequestImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageRequestFactory {
  private final String imagePrompt = "자연스럽고 먹음직스러운 %s 사진을 만들어줘.";
  private final Model model;
  private final String size;

  @Autowired
  public ImageRequestFactory(Model model) {
    this.model = model;
    switch (model) {
      case DallE2 -> this.size = "512x512";
      case DallE3 -> this.size = "1024x1024";
      default -> this.size = "256x256";
    }
  }

  public RequestImageDto create(String menu) {
    return RequestImageDto.builder()
        .model(this.model.getModel())
        .size(this.size)
        .prompt(String.format(this.imagePrompt, menu))
        .responseFormat(ImageResponseType.URL)
        .build();
  }
}
