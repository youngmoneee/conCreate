package com.tistory.chefgpt.application.image.data.res;

import java.util.List;

public record ResponseImageDto(
    long created,
    List<Image> data
) {}