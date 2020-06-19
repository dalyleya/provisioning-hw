package com.voverc.provisioning.controller;

import com.voverc.provisioning.service.ProvisioningService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/provisioning")
public class ProvisioningController {

    @Autowired
    ProvisioningService service;

    @GetMapping("/{macAddress}")
    @ResponseBody
    public String getDevice(@PathVariable String macAddress) {
        String serviceResponse = service.getProvisioningFile(macAddress);
        if (StringUtils.isBlank(serviceResponse)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No device with given mac address found"
            );
        }
        return serviceResponse;
    }
}