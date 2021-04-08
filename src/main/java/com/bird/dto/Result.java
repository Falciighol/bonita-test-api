package com.bird.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.Map;

@JsonDeserialize(builder = Result.ResultBuilder.class)
public class Result {
    private final Map<String, Object> body;
    @JsonIgnore
    private final LocalDate currentDate = LocalDate.now();
    
    public Result(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return this.body;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResultBuilder {
        private Map<String, Object> body;

        public ResultBuilder body(Map<String, Object> body) {
            this.body =  body;
            return this;
        }

        public Result build() {
            return new Result(body);
        }
    }

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }
}

