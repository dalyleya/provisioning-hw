package com.voverc.provisioning.service.parser;

import java.io.IOException;
import java.util.Map;

public interface FragmentParser {

    Map<String, String> parse(String fragment) throws IOException;
}
