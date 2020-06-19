package com.voverc.provisioning.service.parser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFragmentParser.class);

    @Override
    public Map<String, String> parse(String fragment) {
        if (StringUtils.isBlank(fragment)) return new HashMap<>();
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
        } catch (IOException e) {
                LOGGER.warn("Wrong data in override fragment : {}", fragment);
                return null;
        }
        return resultMap;
    }

}
