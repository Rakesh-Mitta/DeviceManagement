package com.glob.database.devicemanagement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "DEVICE_INFO")
public class Device {
    public Device(Long id, String name, String brand, LocalDateTime creationTime) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Device() {
    }

    @Override
    public String toString() {
        return "Device{" + "id=" + id + ", name='" + name + '\'' + ", brand='" + brand + '\'' + ", creationTime=" + creationTime + '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) && Objects.equals(name, device.name) && Objects.equals(brand, device.brand) && Objects.equals(creationTime, device.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, creationTime);
    }

    private String brand;
    @Schema(hidden = true)
    private LocalDateTime creationTime;

}