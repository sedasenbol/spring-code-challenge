package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceCrudRepository extends CrudRepository<Device, Long> {
    List<Device> findByBrandAndModelAndOsAndOsVersion(String brand, String model, String os, String osVersion);


}
