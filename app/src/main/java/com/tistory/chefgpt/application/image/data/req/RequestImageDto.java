package com.tistory.chefgpt.application.image.data.req;

public record RequestImageDto(
    String model,
    String prompt,
    int n,
    String size
) {}
