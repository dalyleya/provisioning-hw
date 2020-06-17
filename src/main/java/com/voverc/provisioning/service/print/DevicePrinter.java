package com.voverc.provisioning.service.print;

import com.voverc.provisioning.service.print.entity.DeviceField;

import java.util.List;

public interface DevicePrinter {
    String print(List<DeviceField> deviceFields);
}
