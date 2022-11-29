package com.canovate.scc.data.saver;

import aj.org.objectweb.asm.TypeReference;
import com.canovate.scc.model.Device;
import com.canovate.scc.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDataSaver {

    private final DeviceService deviceService;

    @Autowired
    public JsonDataSaver(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public List<Device> deviceListSetter()
    {
        ObjectMapper mapper = new ObjectMapper();
        com.fasterxml.jackson.core.type.TypeReference<List<Device>> typeReference = new com.fasterxml.jackson.core.type.TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/devices.json");

        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public void saveDevice(Device device)
    {
        if (!validateDevice(device))
        {
            System.out.println("Unable to save the device; device has empty field(s)");
        }
        else if (!deviceService.exists(device))
        {
            Long id = deviceService.save(device);
            System.out.println("Device was saved with id "+ id);
        }
        else
        {
            System.out.println("Unable to save the device; duplicate entry");
        }
    }

    public boolean validateDevice(Device device)
    {
        return !device.getBrand().isEmpty() &&
                !device.getModel().isEmpty() &&
                !device.getOs().isEmpty() &&
                !device.getOsVersion().isEmpty();
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {

            List<Device> devices = deviceListSetter();

            for (Device device : devices)
            {
               saveDevice(device);
            }

        };
    }
}