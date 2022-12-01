package com.canovate.scc.service;

import com.canovate.scc.model.Device;

public interface DeviceService {
//
//    Iterable<Device> list();

    Long create(Device device);

    Boolean exists(Device device);

    void saveDevice(Device device);

    boolean validateDevice(Device device);

//
//    Page<Device> findByBrand(String brand, Integer page, Integer size);
//
//    Page<Device> findByBrandAndModel(String brand, String model, Integer page, Integer size);
//
//    Page<Device> findByModel(String model, Integer page, Integer size);
}
