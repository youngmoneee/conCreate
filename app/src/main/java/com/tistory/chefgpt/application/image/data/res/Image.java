package com.tistory.chefgpt.application.image.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Image(
    @JsonProperty("b64_json") String b64Json,
    @JsonProperty("url") String url,
    @JsonProperty("revised_prompt") String revisedPrompt
    ) {}