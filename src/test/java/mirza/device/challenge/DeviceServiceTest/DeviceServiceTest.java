package mirza.device.challenge.DeviceServiceTest;

import mirza.device.challenge.model.Device;
import mirza.device.challenge.repository.DeviceRepository;
import mirza.device.challenge.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class DeviceServiceTest {

    private final DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
    private final DeviceService deviceService = new DeviceService(deviceRepository);

    private final String name1 = "device1";
    private final String brand1 = "brand1";

    @Test
    void testAddDevice() {
        Device newDevice = new Device(name1, brand1);
        when(deviceRepository.save(newDevice)).thenReturn(new Device(1L, name1, brand1));

        Device savedDevice = deviceService.addDevice(newDevice);

        assertThat(savedDevice).isNotNull();
        assertThat(savedDevice.getId()).isEqualTo(1L);
        assertThat(savedDevice.getName()).isEqualTo(name1);
        assertThat(savedDevice.getBrand()).isEqualTo(brand1);
    }

    @Test
    void testGetDeviceById() {
        Long deviceId = 1L;
        Device mockDevice = new Device(1L, name1, brand1);
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(mockDevice));

        Optional<Device> result = deviceService.getDeviceById(deviceId);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(name1);
        assertThat(result.get().getBrand()).isEqualTo(brand1);
    }

    @Test
    void testListAllDevices() {
        List<Device> mockDevices = List.of(
                new Device(1L, name1, brand1),
                new Device(2L, "device2", "brand2")
        );
        when(deviceRepository.findAll()).thenReturn(mockDevices);

        List<Device> result = deviceService.listAllDevices();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(name1);
        assertThat(result.get(1).getBrand()).isEqualTo("brand2");
    }

    @Test
    void testUpdateDevice() {
        Long deviceId = 1L;
        Device existingDevice = new Device(deviceId, "oldDevice", "oldBrand");
        Device updatedDevice = new Device("newDevice", "newBrand");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(existingDevice)).thenReturn(new Device(deviceId, "newDevice", "newBrand"));

        Device result = deviceService.updateDevice(deviceId, updatedDevice);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(deviceId);
        assertThat(result.getName()).isEqualTo("newDevice");
        assertThat(result.getBrand()).isEqualTo("newBrand");
    }

    @Test
    void testDeleteDevice() {
        Long deviceId = 1L;
        Mockito.doNothing().when(deviceRepository).deleteById(deviceId);

        deviceService.deleteDevice(deviceId);

        Mockito.verify(deviceRepository, Mockito.times(1)).deleteById(deviceId);
    }

    @Test
    void testSearchDevicesByBrand() {
        List<Device> mockDevices = List.of(
                new Device(name1, brand1),
                new Device("device2", brand1)
        );

        when(deviceRepository.findByBrand(brand1)).thenReturn(mockDevices);

        List<Device> result = deviceService.searchDevicesByBrand(brand1);

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(name1);
        assertThat(result.get(0).getBrand()).isEqualTo(brand1);
    }
}

