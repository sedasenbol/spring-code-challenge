package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceCrudRepository extends CrudRepository<Device, Long> {



}
