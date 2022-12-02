package com.canovate.scc.model;

import com.canovate.scc.types.DeviceOs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "devices")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "brand")
    @NotNull
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "os")
    private DeviceOs os;

    @NotNull
    @Column(name = "os_version")
    private String osVersion;

    public Device() {

    }

    public Device(@NotNull String brand, @NotNull String model, @NotNull String os, @NotNull String osVersion) {
        this.brand = brand;
        this.model = model;
        this.osVersion = osVersion;

        try {
            this.os = DeviceOs.valueOf(os);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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