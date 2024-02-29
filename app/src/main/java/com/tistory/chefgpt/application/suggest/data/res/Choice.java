package com.tistory.chefgpt.application.suggest.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tistory.chefgpt.application.suggest.data.Message;

public record Choice(
    int index,
    Message message,
    @JsonProperty("finish_reason") String finishReason
) {}