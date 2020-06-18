package com.voverc.provisioning.service.print;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.collector.entity.DeviceField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevicePrintResolver {
    @Autowired
    JsonDevicePrinter jsonDevicePrinter;

    @Autowired
    PropertyDevicePrinter propertyDevicePrinter;

    public String printByModel(List<DeviceField> deviceFields, Device.DeviceModel model){
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
