package com.canovate.scc.service;

import com.canovate.scc.model.Device;

public interface DeviceService {

    Iterable<Device> list();

    public Long save(Device device);

    public Boolean exists(Device device);
}
