package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceNativeSearchRepository {

    public List<Device> searchNativeBy(Query query) {
        return query.getResultList();
    }

}
