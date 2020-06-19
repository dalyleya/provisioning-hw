package com.voverc.provisioning.service.collector;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.parser.FragmentParseResolver;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
@PropertySource("classpath:application.properties")
public class DeviceFieldCollector {

    @Autowired
    private Environment env;

    @Autowired
    private FragmentParseResolver parseResolver;

    public Map<String, String> collectFields(Device device) {

        Map<String, String> fields = getDatabaseFields(device);

            Map<String, String> overrideFragmentMap = parseResolver.parseByModel(
                    device.getOverrideFragment(), device.getModel());
            getEnvironmentProperties().entrySet().stream()
                    .filter(e -> !overrideFragmentMap.containsKey(e.getKey()))
                    .forEach(e -> fields.put(e.getKey(), e.getValue()));
            fields.putAll(overrideFragmentMap);
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
