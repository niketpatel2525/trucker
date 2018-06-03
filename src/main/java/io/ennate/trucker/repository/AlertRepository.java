package io.ennate.trucker.repository;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Vehicle;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AlertRepository extends CrudRepository<Alert, String> {
    List<Alert> findByVehicle(Vehicle vehicle);

    @Query("select alert from Alert alert where alert.timestamp between :from and :to and alert.priority=0 order by alert.timestamp, alert.vehicle.vin")
    List<Alert> findHighAlertForAllVehicle(@Param("from") @CreationTimestamp LocalDateTime from, @Param("to") @CreationTimestamp LocalDateTime to);
}
