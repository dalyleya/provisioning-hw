package com.voverc.provisioning.service;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.repository.DeviceRepository;
import com.voverc.provisioning.service.collector.DeviceFieldCollector;
import com.voverc.provisioning.service.print.entity.DeviceField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

    @Autowired
    DeviceRepository repository;

    @Autowired
    DeviceFieldCollector fieldCollector;

    public String getProvisioningFile(String macAddress) {
        Device device = repository.findByMacAddress(macAddress);
        List<DeviceField> deviceFields = fieldCollector.collectFields(device);
        // TODO Implement provisioning
        return null;
    }
}
