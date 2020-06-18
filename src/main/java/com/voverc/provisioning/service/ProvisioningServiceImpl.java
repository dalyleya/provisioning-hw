package com.voverc.provisioning.service;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.repository.DeviceRepository;
import com.voverc.provisioning.service.collector.DeviceFieldCollector;
import com.voverc.provisioning.service.print.DevicePrintResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

    @Autowired
    DeviceRepository repository;

    @Autowired
    DeviceFieldCollector fieldCollector;

    @Autowired
    DevicePrintResolver printResolver;

    public String getProvisioningFile(String macAddress) {
        Device device = repository.findByMacAddress(macAddress);
        Map<String, String> deviceFields = fieldCollector.collectFields(device);
        return printResolver.printByModel(deviceFields, device.getModel());
    }
}
