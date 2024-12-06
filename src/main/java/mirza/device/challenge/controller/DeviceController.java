package mirza.device.challenge.controller;

import mirza.device.challenge.model.Device;
import mirza.device.challenge.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceService.addDevice(device);
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    @GetMapping
    public List<Device> listAllDevices() {
        return deviceService.listAllDevices();
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @RequestBody Device device) {
        return deviceService.updateDevice(id, device);
    }

    @PatchMapping("/{id}")
    public Device partialUpdateDevice(@PathVariable Long id, @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String brand) {
        return deviceService.partialUpdateDevice(id, name, brand);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }

    @GetMapping("/search")
    public List<Device> searchDevicesByBrand(@RequestParam String brand) {
        return deviceService.searchDevicesByBrand(brand);
    }
}
