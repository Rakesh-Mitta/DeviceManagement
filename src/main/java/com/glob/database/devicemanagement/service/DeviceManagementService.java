package com.glob.database.devicemanagement.service;

import com.glob.database.devicemanagement.entity.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public interface DeviceManagementService {

    public List<Device> getAllDevices();

    public Optional<Device> getDeviceById(Long id);

    public Device createDevice(Device device);

    public Device updateDevice(Long id, Device deviceDetails);

    public void deleteDevice(Long id);

    public List<Device> searchDevices(String brand);

    public Device patchDevice(Long id, Map<String, Object> fields);
}
