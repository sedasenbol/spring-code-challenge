package com.canovate.scc.service;

import com.canovate.scc.model.Device;
import com.canovate.scc.repository.DeviceCrudRepository;
import com.canovate.scc.repository.DeviceJpaRepository;
import com.canovate.scc.repository.DeviceNativeSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceCrudRepository deviceCrudRepository;
    private final DeviceJpaRepository deviceJpaRepository;

    private final EntityManager entityManager;

    private final DeviceNativeSearchRepository deviceNativeSearchRepository;

    @Autowired
    public DeviceServiceImpl(DeviceCrudRepository deviceCrudRepository, DeviceJpaRepository deviceJpaRepository, EntityManager entityManager, DeviceNativeSearchRepository deviceNativeSearchRepository) {
        this.deviceCrudRepository = deviceCrudRepository;
        this.deviceJpaRepository = deviceJpaRepository;
        this.entityManager = entityManager;
        this.deviceNativeSearchRepository = deviceNativeSearchRepository;
    }

    @Override
    public Long create(Device device) {
        return deviceCrudRepository.save(device).getId();
    }

    @Override
    public Boolean exists(Device device) {
        return deviceJpaRepository.existsByBrandAndModelAndOsAndOsVersion(device.getBrand(), device.getModel(), device.getOs(), device.getOsVersion());
    }

    @Override
    public List<Device> findBy(String brand, String model, String os, String osVersion, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Query query = buildQuery(brand, model, os, osVersion, pageable);

        return deviceNativeSearchRepository.searchNativeBy(query);
    }

    public Query buildQuery(String brand, String model, String os, String osVersion, Pageable pageable) {

        String s = "select d.* from public.devices d";

        if (brand == null && model == null && os == null && osVersion == null) {
            return entityManager.createNativeQuery(s.toString());
        } else {
            boolean shouldUseAnd = false;
            s += " where ";
            if (brand != null) {
                s += " lower(d.brand) = " + "'" + brand.toLowerCase() + "'";
                shouldUseAnd = true;
            }
            if (model != null) {
                if (shouldUseAnd) {
                    s += " and ";
                }

                s += " lower(d.model) = " + "'" + model.toLowerCase() + "'";
                shouldUseAnd = true;
            }
            if (os != null) {
                if (shouldUseAnd) {
                    s += " and ";
                }

                s += " lower(d.os) = " + "'" + os.toLowerCase() + "'";
                shouldUseAnd = true;
            }
            if (osVersion != null) {
                if (shouldUseAnd) {
                    s += " and ";
                }

                s += " lower(d.os_version) = " + "'" + osVersion.toLowerCase() + "'";
            }
        }

        return entityManager.createNativeQuery(s, Device.class);
    }

    @Override
    public String saveDevice(Device device) {
        if (!validateDevice(device)) {
            return "Unable to save the device; device has empty field(s)";
        } else if (!exists(device)) {
            Long id = create(device);
            return "Device was saved with id " + id;
        } else {
            return "Unable to save the device; duplicate entry";
        }
    }

    @Override
    public boolean validateDevice(Device device) {
        return !device.getBrand().isEmpty() &&
                !device.getModel().isEmpty() &&
                !device.getOs().toString().isEmpty() &&
                !device.getOsVersion().isEmpty();
    }
}
