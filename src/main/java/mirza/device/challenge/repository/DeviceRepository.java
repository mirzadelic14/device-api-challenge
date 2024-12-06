package mirza.device.challenge.repository;

import mirza.device.challenge.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findById(long id);
    Device findByNameAndBrand(String name, String brand);
    List<Device> findAllByName(String name);
    List<Device> findByBrand(String brand);
}
