package io.ennate.trucker.service;

import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.exception.BadRequestException;
import io.ennate.trucker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    VehicleRepository repository;

//    @Transactional
//    public Vehicle create(Vehicle vehicle) {
//        Optional<Vehicle> vehicleExist = repository.findByVin(vehicle.getVin());
//        if (vehicleExist.isPresent()) {
//            throw new BadRequestException("Vehicle with VIN:#" + vehicle.getVin() + " is present in the database.");
//        }
//        return repository.save(vehicle);
//    }

    @Transactional
    public List<Vehicle> create(Iterable<Vehicle> vehicles) {

        List<Vehicle> result = new ArrayList<>();

        if (vehicles == null) {
            return result;
        }
        for (Vehicle v : vehicles) {
            result.add(repository.save(v));
        }

        return result;
    }
}
