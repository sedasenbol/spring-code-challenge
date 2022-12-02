package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class DeviceNativeSearchRepository {

    public Page<Device> searchNativeBy(Query query) {
        return new PageImpl<>(query.getResultList());
    }

}
