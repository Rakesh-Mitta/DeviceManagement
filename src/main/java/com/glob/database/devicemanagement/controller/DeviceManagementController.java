package com.glob.database.devicemanagement.controller;

import com.glob.database.devicemanagement.entity.Device;
import com.glob.database.devicemanagement.service.DeviceManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/device-management")
public class DeviceManagementController {
    private DeviceManagementService deviceManagementServiceImpl;

    public DeviceManagementController(DeviceManagementService deviceManagementServiceImpl) {
        this.deviceManagementServiceImpl = deviceManagementServiceImpl;
    }


    @Operation(summary = "Get all the existing devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the devices"),
            @ApiResponse(responseCode = "404", description = "Device/s not existing")
    })
    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        if (deviceManagementServiceImpl.getAllDevices().size() > 0) {
            return ResponseEntity.ok(deviceManagementServiceImpl.getAllDevices());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Get the device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the device"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return deviceManagementServiceImpl.getDeviceById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device added successfully")
    })
    @PostMapping("/devices")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device createdDevice = deviceManagementServiceImpl.createDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @Operation(summary = "Update the device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the device"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PutMapping("/devices/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        try {
            Device updatedDevice = deviceManagementServiceImpl.updateDevice(id, deviceDetails);
            return ResponseEntity.ok(updatedDevice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the device"),
            @ApiResponse(responseCode = "204", description = "No Device existing/not found")
    })
    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        try {
            deviceManagementServiceImpl.deleteDevice(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }


    }

    @Operation(summary = "Search the device by brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the device/s"),
            @ApiResponse(responseCode = "404", description = "Device not found/existed")
    })
    @GetMapping("/devices/search")
    public ResponseEntity<List<Device>> searchProducts(
            @RequestParam(required = false) String brand) {
        if (deviceManagementServiceImpl.searchDevices(brand).size() > 0) {
            return ResponseEntity.ok(deviceManagementServiceImpl.searchDevices(brand));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Patch the device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patched/Updated the specified fields of device"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PatchMapping("/devices/{id}")
    public ResponseEntity<Device> patchDevice(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        try {
            Device updatedDevice = deviceManagementServiceImpl.patchDevice(id, fields);
            return ResponseEntity.ok(updatedDevice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



