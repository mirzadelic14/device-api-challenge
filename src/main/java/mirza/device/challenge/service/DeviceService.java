package mirza.device.challenge.service;

import jakarta.persistence.EntityNotFoundException;
import mirza.device.challenge.model.Device;
import mirza.device.challenge.repository.DeviceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device addDevice(Device device) {
        if (device.getName() == null || device.getBrand() == null) {
            throw new DataIntegrityViolationException("Invalid data");
        }
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> listAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Long id, Device updatedDevice) {
        return deviceRepository.findById(id).map(device -> {
            device.setName(updatedDevice.getName());
            device.setBrand(updatedDevice.getBrand());
            return deviceRepository.save(device);
        }).orElseThrow(() -> new EntityNotFoundException("Device not found"));
    }

    public Device partialUpdateDevice(Long id, String name, String brand) {
        return deviceRepository.findById(id).map(device -> {
            if (name != null) device.setName(name);
            if (brand != null) device.setBrand(brand);
            return deviceRepository.save(device);
        }).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> searchDevicesByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }
}
