package com.voverc.provisioning.service.collector;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.service.parser.FragmentParseResolver;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceFieldCollectorTest {

    private final DeviceFieldCollector deviceFieldCollector = new DeviceFieldCollector();

    private Environment env;

    private FragmentParseResolver parseResolver;

    @Test
    void collectFields() {
        env = mock(Environment.class);
        parseResolver = mock(FragmentParseResolver.class);

        when(env.getProperty(anyString())).thenReturn("property");

        Map<String, String> mockedMap = new HashMap<>();
        mockedMap.put("domain", "parameter");
        mockedMap.put("port", "parameter");
        mockedMap.put("timeout", "parameter");
        when(parseResolver.parseByModel(any(String.class),
                any(Device.DeviceModel.class))).thenReturn(mockedMap);

        deviceFieldCollector.setEnv(env);
        deviceFieldCollector.setParseResolver(parseResolver);

        Device device = new Device();
        device.setOverrideFragment("");
        device.setMacAddress("data");
        device.setModel(Device.DeviceModel.CONFERENCE);
        device.setUsername("data");
        device.setPassword("data");
        Map<String, String>  result = deviceFieldCollector.collectFields(device);
        Map<String, String>  expected = new HashMap<>();
        expected.put("username", "data");
        expected.put("password", "data");
        expected.put("codecs", "property");
        expected.put("port", "parameter");
        expected.put("domain", "parameter");
        expected.put("timeout", "parameter");

        assertEquals(expected, result);
    }

    @Test
    void collectFieldsWithNoFragment() {
        env = mock(Environment.class);
        parseResolver = mock(FragmentParseResolver.class);

        when(env.getProperty(anyString())).thenReturn("property");

        when(parseResolver.parseByModel(any(String.class),
                any(Device.DeviceModel.class))).thenReturn(new HashMap<>());

        deviceFieldCollector.setEnv(env);
        deviceFieldCollector.setParseResolver(parseResolver);

        Device device = new Device();
        device.setOverrideFragment("");
        device.setMacAddress("data");
        device.setModel(Device.DeviceModel.CONFERENCE);
        device.setUsername("data");
        device.setPassword("data");
        Map<String, String>  result = deviceFieldCollector.collectFields(device);
        Map<String, String>  expected = new HashMap<>();
        expected.put("username", "data");
        expected.put("password", "data");
        expected.put("codecs", "property");
        expected.put("port", "property");
        expected.put("domain", "property");

        assertEquals(expected, result);
    }
}