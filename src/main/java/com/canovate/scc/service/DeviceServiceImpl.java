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

//    @Override
//    public Iterable<Device> list() {
//        return deviceCrudRepository.findAll();
//    }

    @Override
    public Long create(Device device) {
        return deviceCrudRepository.save(device).getId();
    }

    @Override
    public Boolean exists(Device device){
        return deviceJpaRepository.existsByBrandAndModelAndOsAndOsVersion(device.getBrand(), device.getModel(), device.getOs(), device.getOsVersion());
    }


//
//    @Override
//    public Page<Device> findByBrand(String brand, Integer page, Integer size)
//    {
//        PageRequest pageRequest = PageRequest.of(page,size);
//
//        return deviceJpaRepository.findByBrand(brand, pageRequest);
//    }
//
//    @Override
//    public Page<Device> findByBrandAndModel(String brand, String model, Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of(page,size);
//
//        return deviceJpaRepository.findByBrandAndModel(brand, model, pageRequest);
//    }
//
//    @Override
//    public Page<Device> findByModel(String model, Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of(page,size);
//
//        return deviceJpaRepository.findByModel(model, pageRequest);
//    }

    @Override
    public void saveDevice(Device device)
    {
        if (!validateDevice(device))
        {
            System.out.println("Unable to save the device; device has empty field(s)");
        }
        else if (!exists(device))
        {
            Long id = create(device);
            System.out.println("Device was saved with id "+ id);
        }
        else
        {
            System.out.println("Unable to save the device; duplicate entry");
        }
    }

    @Override
    public boolean validateDevice(Device device)
    {
        return !device.getBrand().isEmpty() &&
                !device.getModel().isEmpty() &&
                !device.getOs().toString().isEmpty() &&
                !device.getOsVersion().isEmpty();
    }
}
