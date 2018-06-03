package io.ennate.trucker.service;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.exception.BadRequestException;
import io.ennate.trucker.exception.VehicleNotFoundException;
import io.ennate.trucker.repository.AlertRepository;
import io.ennate.trucker.repository.ReadingRepository;
import io.ennate.trucker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ReadingRepository readingRepository;

    @Transactional
    public List<Vehicle> create(Iterable<Vehicle> vehicles) {

        List<Vehicle> result = new ArrayList<>();

        if (vehicles == null) {
            return result;
        }
        for (Vehicle v : vehicles) {
            result.add(vehicleRepository.save(v));
        }

        return result;
    }

    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public List<Alert> findAllAlertByVehicle(String vin) {
        Optional<Vehicle> vehicleExist = vehicleRepository.findByVin(vin);
        if (!vehicleExist.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with VIN:#" + vin + " not available.");
        }
        System.out.println("Service");
        return alertRepository.findByVehicle(vehicleExist.get());
    }

    @Override
    public List<Map<String, String>> findLocationOfAllVehicles(String vin) {
        Optional<Vehicle> vehicleExist = vehicleRepository.findByVin(vin);
        if (!vehicleExist.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with VIN:#" + vin + " not available.");
        }
        LocalDateTime current = LocalDateTime.now();
        return readingRepository.findLocationByVehicle(current.minusMinutes(30), current, vehicleExist.get());
    }

    @Override
    public List<Alert> findHighAlertForAllVehicle() {
        LocalDateTime current = LocalDateTime.now();
        return alertRepository.findHighAlertForAllVehicle(current.minusHours(2), current);
    }
}
