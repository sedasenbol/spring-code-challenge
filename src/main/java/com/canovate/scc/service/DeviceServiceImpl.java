package com.canovate.scc.service;

import com.canovate.scc.model.Device;
import com.canovate.scc.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Iterable<Device> list() {
        return deviceRepository.findAll();
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Iterable<Device> save(List<Device> devices) {
        return deviceRepository.saveAll(devices);
    }
}
