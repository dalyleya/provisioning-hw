package com.voverc.provisioning.service.print;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonDevicePrinter implements DevicePrinter {
    @Override
    public String print(Map<String, String> deviceFields) {
        return new Gson().toJson(deviceFields);
    }
}
