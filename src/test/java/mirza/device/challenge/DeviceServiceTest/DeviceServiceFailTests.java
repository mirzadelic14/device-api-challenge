package mirza.device.challenge.DeviceServiceTest;

import jakarta.persistence.EntityNotFoundException;
import mirza.device.challenge.model.Device;
import mirza.device.challenge.repository.DeviceRepository;
import mirza.device.challenge.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class DeviceServiceFailTests {

    private final DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
    private final DeviceService deviceService = new DeviceService(deviceRepository);

    @Test
    void testAddDeviceInvalidData() {
        Device invalidDevice = new Device("", null);

        assertThatThrownBy(() -> deviceService.addDevice(invalidDevice))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testGetDeviceByIdNotFound() {
        Long nonExistentId = 999L;
        when(deviceRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<Device> result = deviceService.getDeviceById(nonExistentId);
        assertThat(result).isEmpty();
    }

    @Test
    void testListAllDevicesEmptyDatabase() {
        when(deviceRepository.findAll()).thenReturn(List.of());

        List<Device> result = deviceService.listAllDevices();

        assertThat(result).isEmpty();
    }

    @Test
    void testUpdateDeviceNotFound() {
        Long nonExistentId = 999L;
        Device updatedDevice = new Device("newDevice", "newBrand");
        when(deviceRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> deviceService.updateDevice(nonExistentId, updatedDevice))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Device not found");
    }

    @Test
    void testDeleteDeviceNotFound() {
        Long nonExistentId = 999L;
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(deviceRepository).deleteById(nonExistentId);

        assertThatThrownBy(() -> deviceService.deleteDevice(nonExistentId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}
