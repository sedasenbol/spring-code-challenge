package com.canovate.scc.service;

import com.canovate.scc.model.Device;
import com.canovate.scc.repository.DeviceCrudRepository;
import com.canovate.scc.repository.DeviceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService{

    private DeviceCrudRepository deviceCrudRepository;
    private DeviceJpaRepository deviceJpaRepository;

    @Autowired
    public DeviceServiceImpl(DeviceCrudRepository deviceCrudRepository, DeviceJpaRepository deviceJpaRepository) {
        this.deviceCrudRepository = deviceCrudRepository;
        this.deviceJpaRepository = deviceJpaRepository;
    }

    @Override
    public Iterable<Device> list() {
        return deviceCrudRepository.findAll();
    }

    @Override
    public Long save(Device device) {
        return deviceCrudRepository.save(device).getId();
    }

    @Override
    public Boolean exists(Device device){
        return deviceJpaRepository.existsByBrandAndModelAndOsAndOsVersion(device.getBrand(), device.getModel(), device.getOs(), device.getOsVersion());
    }
}
