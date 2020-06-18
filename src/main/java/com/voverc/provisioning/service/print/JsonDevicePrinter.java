package com.voverc.provisioning.service.print;

import com.voverc.provisioning.service.collector.entity.DeviceField;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonDevicePrinter implements DevicePrinter {
    @Override
    public String print(List<DeviceField> deviceFields) {
        //TODO
        return null;
    }
}
