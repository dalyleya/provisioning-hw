package com.voverc.provisioning.service.print;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PropertyDevicePrinterTest {

    private final PropertyDevicePrinter printer = new PropertyDevicePrinter();

    @Test
    void print() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("domain", "sip.anotherdomain.com");
        data.put("port", "5161");
        data.put("timeout", "10");
        String result = printer.print(data);
        String expected = "domain=sip.anotherdomain.com\n" +
                "port=5161\n" +
                "timeout=10";
        assertEquals(expected, result);
    }

    @Test
    void printEmptyMep() {
        Map<String, String> data = new HashMap<>();
        String result = printer.print(data);
        String expected = "";
        assertEquals(expected, result);
    }
}