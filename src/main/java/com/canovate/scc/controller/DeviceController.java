package com.canovate.scc.controller;

import com.canovate.scc.model.Device;
import com.canovate.scc.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    @ResponseBody
    public String createDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    @PostMapping(value = "/createBatch", consumes = "application/json")
    @ResponseBody
    public String createDevices(@RequestBody Device[] devices) {

        StringBuilder s = new StringBuilder("");

        for (Device device : devices) {
            s.append(deviceService.saveDevice(device));
            s.append(System.lineSeparator());
        }

        return s.toString();
    }

    @GetMapping(
            value = "/search"
            //params = {"brand", "model", "os", "osVersion", "page", "size"}
    )
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Page<Device> findBy(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                               @RequestParam(value = "size", defaultValue = "5", required = false) Integer size,
                               @RequestParam(value = "brand", required = false) String brand,
                               @RequestParam(value = "model", required = false) String model,
                               @RequestParam(value = "os", required = false) String os,
                               @RequestParam(value = "osVersion", required = false) String osVersion) {
        return deviceService.findBy(brand, model, os, osVersion, page, size);
    }
}

