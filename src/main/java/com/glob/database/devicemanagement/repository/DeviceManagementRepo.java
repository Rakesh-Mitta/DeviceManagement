package com.glob.database.devicemanagement.repository;

import com.glob.database.devicemanagement.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceManagementRepo extends JpaRepository<Device, Long> {
    @Query("SELECT d FROM Device d WHERE " +
            "(:brand IS NULL OR d.brand LIKE %:brand%)")
    List<Device> searchDevices(
            @Param("brand") String brand);
}
