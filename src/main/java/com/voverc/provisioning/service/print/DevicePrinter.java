package com.voverc.provisioning.service.print;

import java.util.Map;

public interface DevicePrinter {
    String print(Map<String, String> deviceFields);
}
