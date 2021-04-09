package com.bird.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.Map;

@JsonDeserialize(builder = Result.ResultBuilder.class)
public class Result {
    private final Map<String, Object> result;
    @JsonIgnore
    private final LocalDate currentDate = LocalDate.now();
    
    public Result(Map<String, Object> result) {
        this.result = result;
    }

    public Map<String, Object> getBody() {
        return this.result;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResultBuilder {
        private Map<String, Object> result;

        public ResultBuilder result(Map<String, Object> result) {
            this.result =  result;
            return this;
        }

        public Result build() {
            return new Result(result);
        }
    }

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }
}

