package com.glob.database.devicemanagement.service;

import com.glob.database.devicemanagement.entity.Device;
import com.glob.database.devicemanagement.repository.DeviceManagementRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DeviceManagementServiceImplTest {

    @Mock
    private DeviceManagementRepo deviceManagementRepo;
    private DeviceManagementService deviceManagementService;
    AutoCloseable autoCloseable;
    Device device;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        deviceManagementService = new DeviceManagementServiceImpl(deviceManagementRepo);
        device = new Device(1L, "iPhone", "apple", getCurrentDateTime());
    }

    private LocalDateTime getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetAllDevices() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.findAll()).thenReturn(new ArrayList<Device>(Collections.singleton(device)));
        assertThat(deviceManagementService.getAllDevices().get(0).getName()).isEqualTo("iPhone");
    }

    @Test
    void testGetDeviceById() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.findById(1L)).thenReturn(Optional.ofNullable(device));
        assertThat(deviceManagementService.getDeviceById(1L).get().getBrand()).isEqualTo("apple");
    }

    @Test
    void testCreateDevice() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.save(device)).thenReturn(device);
        assertThat(deviceManagementService.createDevice(device)).isEqualTo(device);
    }

    @Test
    void testUpdateDevice() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.findById(1L)).thenReturn(Optional.ofNullable(device));
        when(deviceManagementRepo.save(device)).thenReturn(device);
        assertThat(deviceManagementService.updateDevice(1L, device)).isEqualTo(device);
    }

    @Test
    void testSearchDevices() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.searchDevices("apple")).thenReturn(new ArrayList<Device>(Collections.singleton(device)));
        assertThat(deviceManagementService.searchDevices("apple").get(0).getName()).isEqualTo("iPhone");
    }

    @Test
    void testPatchDevice() {
        mock(Device.class);
        mock(DeviceManagementRepo.class);
        when(deviceManagementRepo.findById(1L)).thenReturn(Optional.ofNullable(device));
        when(deviceManagementRepo.save(device)).thenReturn(device);
        assertThat(deviceManagementService.updateDevice(1L, device)).isEqualTo(device);
    }
}