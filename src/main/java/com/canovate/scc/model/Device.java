package com.canovate.scc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter@Setter
@Table(name="devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "os")
    private String os;

    @Column(name = "os_version")
    private String osVersion;

    public Device() {

    }

    public Device(String brand, String model, String os, String osVersion) {
        this.brand = brand;
        this.model = model;
        this.os = os;
        this.osVersion = osVersion;
        //this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}