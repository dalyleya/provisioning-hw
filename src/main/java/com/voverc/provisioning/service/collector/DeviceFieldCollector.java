package com.voverc.provisioning.service.collector;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.parser.FragmentParseResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@PropertySource("classpath:application.properties")
public class DeviceFieldCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceFieldCollector.class);

    @Autowired
    private Environment env;

    @Autowired
    private FragmentParseResolver parseResolver;

    public Map<String, String> collectFields(Device device) {

        Map<String, String> fields = getDatabaseFields(device);
        try {
            fields.putAll(parseResolver.parseByModel(
                    device.getOverrideFragment(), device.getModel()));
        } catch (IOException e) {
            LOGGER.warn("Something is wrong with override fragment : {}", device.getOverrideFragment());
            e.printStackTrace();
        }
        for (Map.Entry<String, String> e : getEnvironmentProperties().entrySet()) {
            fields.putIfAbsent(e.getKey(), e.getValue());
        }
        return fields;
    }

    private Map<String, String> getDatabaseFields(Device device) {
        Map<String, String> databaseFields = new LinkedHashMap<>();
        databaseFields.put("username", device.getUsername());
        databaseFields.put("password", device.getPassword());
        return databaseFields;
    }

    private Map<String, String> getEnvironmentProperties() {
        Map<String, String> envs = new HashMap<>(3);
        envs.put("domain", env.getProperty("provisioning.domain"));
        envs.put("port", env.getProperty("provisioning.port"));
        envs.put("codecs", env.getProperty("provisioning.codecs"));
        return envs;
    }
}
