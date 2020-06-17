package com.voverc.provisioning.service.print.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceField {

    private String field;
    private String value;
    private FieldUpdateStatus status;

    public enum FieldUpdateStatus {
        DATABASE("From database"),
        PROPERTIES("From application.properties"),
        REPLACED_PROPERTIES("From override fragment (replaced application.properties)"),
        ADDED("From override fragment (added)");

        private final String verboseStatus;

        FieldUpdateStatus(String verboseStatus) {
            this.verboseStatus = verboseStatus;
        }

    }
}
