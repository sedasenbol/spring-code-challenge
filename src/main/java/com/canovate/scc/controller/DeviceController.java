package com.canovate.scc.controller;

import com.canovate.scc.model.Device;
import com.canovate.scc.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    private final ModelMapper modelMapper;
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(ModelMapper modelMapper, DeviceService deviceService) {
        this.modelMapper = modelMapper;
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

    @GetMapping(value = "/search")
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public List<Device> findBy(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                  @RequestParam(value = "size", defaultValue = "5", required = false) Integer size,
                                  @RequestParam(value = "brand", required = false) String brand,
                                  @RequestParam(value = "model", required = false) String model,
                                  @RequestParam(value = "os", required = false) String os,
                                  @RequestParam(value = "osVersion", required = false) String osVersion) {

        List<Device> entities = deviceService.findBy(brand, model, os, osVersion, page, size);
        return entities;
        //return entities.stream().map(device -> modelMapper.map(device, DeviceDto.class))
        //        .collect(Collectors.toList());
    }
}

