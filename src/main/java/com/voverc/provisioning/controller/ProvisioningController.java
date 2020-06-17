package com.voverc.provisioning.controller;

import com.voverc.provisioning.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/provisioning")
public class ProvisioningController {

    @Autowired
    ProvisioningService service;

//    @GetMapping("/{macAddress:^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$}")
    @GetMapping("/{macAddress}")
    @ResponseBody
    public String getDevice(@PathVariable String macAddress) {
        return service.getProvisioningFile(macAddress);
    }

}