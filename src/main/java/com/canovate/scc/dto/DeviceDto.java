package com.canovate.scc.dto;

import com.canovate.scc.types.DeviceOs;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DeviceDto {

    private Long id;
    private String brand;
    private String model;
    private DeviceOs os;
    private String osVersion;

    public DeviceDto() {
    }
}
