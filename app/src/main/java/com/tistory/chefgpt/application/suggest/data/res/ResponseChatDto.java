package com.tistory.chefgpt.application.suggest.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResponseChatDto(
    String id,
    String object,
    long created,
    String model,
    @JsonProperty("system_fingerprint")
    String systemFingerprint,
    List<Choice> choices,
    Usage usage
) {}