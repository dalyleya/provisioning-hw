package com.voverc.provisioning.service.parser;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * An alternative class for parsing properties.
 * It's made in a manual way, but it doesn't throw useless exceptions.
 */
@Component
public class SimplePropertyFragmentParser implements FragmentParser {
    @Override
    public Map<String, String> parse(String fragment) {
        String[] properties = fragment.split("\n");
        Map<String, String> resultMap = new HashMap<>();
        for (String s : properties) {
            String[] property = s.split("=");
            resultMap.put(property[0], property[1]);
        }
        return resultMap;
    }
}
