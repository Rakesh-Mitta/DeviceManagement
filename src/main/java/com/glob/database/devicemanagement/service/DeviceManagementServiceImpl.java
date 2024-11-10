package com.glob.database.devicemanagement.service;

import com.glob.database.devicemanagement.entity.Device;
import com.glob.database.devicemanagement.repository.DeviceManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeviceManagementServiceImpl implements DeviceManagementService {


    private DeviceManagementRepo deviceManagementRepo;

    public DeviceManagementServiceImpl(DeviceManagementRepo deviceManagementRepo) {
        this.deviceManagementRepo = deviceManagementRepo;
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceManagementRepo.findAll();
    }

    @Override
    public Optional<Device> getDeviceById(Long id) {
        return deviceManagementRepo.findById(id);
    }

    @Override
    public Device createDevice(Device device) {
        device.setCreationTime(getCurrentDateTime());
        return deviceManagementRepo.save(device);
    }

    @Override
    public Device updateDevice(Long id, Device deviceDetails) {

        return deviceManagementRepo.findById(id).map(device -> {
            device.setName(deviceDetails.getName());
            device.setBrand(deviceDetails.getBrand());
            device.setCreationTime(getCurrentDateTime());
            return deviceManagementRepo.save(device);
        }).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    private LocalDateTime getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);

    }

    @Override
    public void deleteDevice(Long id) {
        if (deviceManagementRepo.existsById(id)) {
            deviceManagementRepo.deleteById(id);
        } else {
            throw new RuntimeException("Device not existing");
        }
    }

    @Override
    public List<Device> searchDevices(String brand) {
        return deviceManagementRepo.searchDevices(brand);
    }

    @Override
    public Device patchDevice(Long id, Map<String, Object> fields) {
        Device existingDevice = deviceManagementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Device.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingDevice, value);
        });
        return deviceManagementRepo.save(existingDevice);

    }

}

