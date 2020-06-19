package com.voverc.provisioning.service.parser;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFragmentParserTest {

    private final PropertyFragmentParser parser = new PropertyFragmentParser();

    @Test
    void parseRegularPayload() {
        String fragment = "domain=sip.anotherdomain.com\n" +
                "port=5161\n" +
                "timeout=10";
        Map<String, String> actual = parser.parse(fragment);
        Map<String, String> expected = new HashMap<>();
        expected.put("domain", "sip.anotherdomain.com");
        expected.put("port", "5161");
        expected.put("timeout", "10");
        assertEquals(expected, actual);
    }

    @Test
    void parseEmptyString() {
        String fragment = "";
        assertEquals(new HashMap<>(), parser.parse(fragment));
    }

    @Test
    void parseNull() {
        String fragment = null;
        assertEquals(new HashMap<>(), parser.parse(fragment));
    }
}