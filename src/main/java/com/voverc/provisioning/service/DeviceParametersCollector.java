package com.voverc.provisioning.service;

import java.util.Map;

public interface DeviceParametersCollector {
    Map<String,String> combine();

    public class DevicePrintEntity{
        private String value;

    }
}
