package com.example.gateway.config;

import com.baitao.common.response.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by hrhrh on 2020/4/26 11:18
 */
@Component
public class ResultJacksonJsonParser extends JacksonJsonParser {

    private static final TypeReference<?> RESULT_TYPE = new ResultTypeReference();

    private ObjectMapper objectMapper;

    public Result<Map<String, String>> parseResult(String json) {
        return tryParse(() -> getObjectMapper().readValue(json, RESULT_TYPE),
                Exception.class);
    }


    public ObjectMapper getObjectMapper() {
        if (this.objectMapper == null) {
            this.objectMapper = new ObjectMapper();
        }
        return this.objectMapper;
    }

    private static class ResultTypeReference extends TypeReference<Result<Map<String, String>>> {
    }
}
