package io.ennate.trucker.service;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> create(Iterable<Vehicle> vehicle);

    List<Vehicle> findAll();

    List<Alert> findAllAlertByVehicle(String vin);

    public List<Map<String, String>> findLocationOfAllVehicles(String vin);

    public List<Alert> findHighAlertForAllVehicle();
}
