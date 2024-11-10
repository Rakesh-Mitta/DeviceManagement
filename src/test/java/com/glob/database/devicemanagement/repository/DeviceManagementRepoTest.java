package com.glob.database.devicemanagement.repository;

import com.glob.database.devicemanagement.entity.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DeviceManagementRepoTest {

    @Autowired
    private DeviceManagementRepo deviceManagementRepo;
    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device(1L, "iPhone", "apple", getCurrentDateTime());
        deviceManagementRepo.save(device);
    }

    @AfterEach
    void tearDown() {
        device = null;
        deviceManagementRepo.deleteAll();
    }

    @Test
    void testSearchDevices_FOUND() throws InterruptedException {
        List<Device> devices = deviceManagementRepo.searchDevices("apple");
        assertThat(devices.get(0).getName()).isEqualTo("iPhone");
    }

    @Test
    void testSearchDevices_NOTFOUND() throws InterruptedException {
        List<Device> devices = deviceManagementRepo.searchDevices("samsung");
        assertThat(devices.isEmpty()).isTrue();
    }

    private LocalDateTime getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);

    }
}