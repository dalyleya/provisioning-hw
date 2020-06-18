package com.voverc.provisioning.service.parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JsonFragmentParser implements FragmentParser {

    @Override
    public Map<String, String> parse(String jsonFragment) {

        JsonObject jsonObject = JsonParser.parseString(jsonFragment).getAsJsonObject();
        return jsonObject.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getAsString()));
    }
}
