package com.voverc.provisioning.service.collector;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.parser.FragmentParseResolver;
import com.voverc.provisioning.service.collector.entity.DeviceField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:application.properties")
public class DeviceFieldCollector {

    @Autowired
    private Environment env;

    @Autowired
    private FragmentParseResolver parseResolver;

    public List<DeviceField> collectFields(Device device) {

        List<DeviceField> fields = new LinkedList<>(getDatabaseFields(device));

        Map<String, String> fragmentMap =
                parseResolver.parseByModel(device.getOverrideFragment(), device.getModel());

        fields.addAll(mergeFragmentWithEnvs(fragmentMap));

        return fields;
    }

    private List<DeviceField> mergeFragmentWithEnvs(Map<String, String> fragmentMap) {
        Map<String, String> envs = getEnvironmentProperties();
        List<DeviceField> fields = getEnvironmentProperties().entrySet().stream()
                .filter(e -> !fragmentMap.containsKey(e.getKey()))
                .map(e -> new DeviceField(e.getKey(), e.getValue(), DeviceField.FieldUpdateStatus.PROPERTIES))
                .collect(Collectors.toList());

        fields.addAll(fragmentMap.entrySet().stream()
                .map(e -> new DeviceField(e.getKey(), e.getValue(),
                        envs.containsKey(e.getKey()) ?
                                DeviceField.FieldUpdateStatus.REPLACED_PROPERTIES
                                : DeviceField.FieldUpdateStatus.ADDED))
                .collect(Collectors.toList()));

        return fields;
    }

    private List<DeviceField> getDatabaseFields(Device device) {
        List<DeviceField> databaseFields = new ArrayList<>(2);
        databaseFields.add(new DeviceField("username", device.getUsername(), DeviceField.FieldUpdateStatus.DATABASE));
        databaseFields.add(new DeviceField("password", device.getPassword(), DeviceField.FieldUpdateStatus.DATABASE));
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
