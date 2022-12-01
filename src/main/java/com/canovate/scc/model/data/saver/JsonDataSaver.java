package com.canovate.scc.model.data.saver;

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

    @Bean
    public CommandLineRunner runner() {
        return args -> {

            List<Device> devices = deviceListSetter();

            for (Device device : devices)
            {
               deviceService.saveDevice(device);
            }

        };
    }
}
