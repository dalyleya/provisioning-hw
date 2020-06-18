package com.voverc.provisioning.service.parser;

import com.voverc.provisioning.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FragmentParseResolver {

    @Autowired
    JsonFragmentParser jsonParser;

    @Autowired
    PropertyFragmentParser propertyParser;

    public Map<String, String> parseByModel(String fragment, Device.DeviceModel model){
        Map<String, String> result;
        switch (model){
            case DESK:
                result = propertyParser.parse(fragment);
                break;
            case CONFERENCE:
                result = jsonParser.parse(fragment);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model);
        }
        return result;
    }
}
