package io.ennate.trucker.service;

import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.exception.VehicleNotFoundException;
import io.ennate.trucker.repository.AlertRepository;
import io.ennate.trucker.repository.ReadingRepository;
import io.ennate.trucker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ReadingServiceImp implements ReadingService {

    @Autowired
    private ReadingRepository rRepository;

    @Autowired
    private VehicleRepository vRepository;
    
    @Transactional
    public void create(Reading reading) {
        Optional<Vehicle> vehicleExist = vRepository.findByVin(reading.getVehicle().getVin());
        if (!vehicleExist.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with VIN:#" + reading.getVehicle().getVin() + " not available.");
        }
        reading.setVehicle(vehicleExist.get());
        rRepository.save(reading);
    }
}
