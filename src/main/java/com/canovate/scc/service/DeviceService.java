package com.canovate.scc.service;

import com.canovate.scc.model.Device;

import java.util.List;

public interface DeviceService {

    Long create(Device device);

    Boolean exists(Device device);

    String saveDevice(Device device);

    boolean validateDevice(Device device);

    List<Device> findBy(String brand, String model, String os, String osVersion, int page, int size);

}
