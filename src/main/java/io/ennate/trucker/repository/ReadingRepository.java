package io.ennate.trucker.repository;

import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, String> {
//    Optional<Vehicle> findByVehicle(Vehicle vehicle);
}
