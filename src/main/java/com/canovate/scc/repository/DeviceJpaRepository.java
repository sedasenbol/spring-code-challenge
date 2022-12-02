package com.canovate.scc.repository;

import com.canovate.scc.model.Device;
import com.canovate.scc.types.DeviceOs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "devices")
public interface DeviceJpaRepository extends JpaRepository<Device, Long> {

    @Query("select case when count(d)>0 then true else false end from Device d where d.brand = :brand and d.model = :model and d.os = :os and d.osVersion = :os_version")
    Boolean existsByBrandAndModelAndOsAndOsVersion(@Param("brand") String brand, @Param("model") String model, @Param("os") DeviceOs os, @Param("os_version") String osVersion);
}
