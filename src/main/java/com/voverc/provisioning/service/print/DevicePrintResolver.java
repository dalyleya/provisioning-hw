package com.voverc.provisioning.service.print;

import com.voverc.provisioning.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DevicePrintResolver {
    @Autowired
    JsonDevicePrinter jsonDevicePrinter;

    @Autowired
    PropertyDevicePrinter propertyDevicePrinter;

    public String printByModel(Map<String, String> deviceFields, Device.DeviceModel model){
        String result;
        switch (model){
            case DESK:
                result = propertyDevicePrinter.print(deviceFields);
                break;
            case CONFERENCE:
                result = jsonDevicePrinter.print(deviceFields);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model);
        }
        return result;
    }
}
