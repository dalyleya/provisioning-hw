package com.voverc.provisioning.service.parser;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PropertyFragmentParser implements FragmentParser {

    @Override
    public Map<String, String> parse(String fragment) throws IOException {

        Map<String, String> resultMap;
        Properties properties = new Properties();
        try (InputStream inputStream = new ByteArrayInputStream(fragment.getBytes())) {

            properties.load(inputStream);
            resultMap = new HashMap<>(properties.entrySet().stream().collect(
                    Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue().toString()
                    )
            ));
        }
        return resultMap;
    }

}
