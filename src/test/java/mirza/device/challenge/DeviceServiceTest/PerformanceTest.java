package mirza.device.challenge.DeviceServiceTest;

import mirza.device.challenge.model.Device;
import mirza.device.challenge.repository.DeviceRepository;
import mirza.device.challenge.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

public class PerformanceTest {
    private final DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
    private final DeviceService deviceService = new DeviceService(deviceRepository);

    @Test
    void testListAllDevices_LargeDataset() {
        List<Device> mockDevices = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            mockDevices.add(new Device(i, "device" + i, "brand" + (i % 10)));
        }
        when(deviceRepository.findAll()).thenReturn(mockDevices);

        List<Device> result = deviceService.listAllDevices();

        assertThat(result).hasSize(1000000);
    }

}
