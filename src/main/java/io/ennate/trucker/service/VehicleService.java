package io.ennate.trucker.service;

import io.ennate.trucker.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> create(Iterable<Vehicle> vehicle);

    List<Vehicle> findAll();
}
