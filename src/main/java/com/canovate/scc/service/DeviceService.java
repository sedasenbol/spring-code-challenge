package com.canovate.scc.service;

import com.canovate.scc.model.Device;

import java.util.List;

public interface DeviceService {

    Iterable<Device> list();

    public Device save(Device device);

    public Iterable<Device> save(List<Device> devices);

}
