package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeviceNativeSearchRepository {

    public List<Device> searchNativeBy(Query query) {

       return query.getResultList();
    }

}
