package com.voverc.provisioning.service.collector;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.print.entity.DeviceField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DeviceFieldCollector {

    @Autowired
    private Environment env;

    public List<DeviceField> collectFields(Device device) {
        List<DeviceField> fields = new LinkedList<>();
        fields.add(new DeviceField("username", device.getUsername(), DeviceField.FieldUpdateStatus.DATABASE));
        fields.add(new DeviceField("password", device.getPassword(), DeviceField.FieldUpdateStatus.DATABASE));


//        env.getProperty("provisioning.codecs");
        return fields;
    }
}
