package com.voverc.provisioning.service.print;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PropertyDevicePrinter implements DevicePrinter {
    @Override
    public String print(Map<String, String> deviceFields) {
        return StringUtils.join(deviceFields.entrySet().toArray(), "\n");
    }
}
