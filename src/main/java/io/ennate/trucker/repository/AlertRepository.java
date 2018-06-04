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

public interface AlertRepository extends CrudRepository<Alert, String> {
    List<Alert> findByVehicle(Vehicle vehicle);

    @Query("select alert.message as message,alert.timestamp as time from Alert alert where alert.timestamp between :from and :to and alert.priority=0 and alert.vehicle=:vehicle order by alert.timestamp")
    List<Map<String, Object>> findHighAlertForAllVehicle(@Param("from") @CreationTimestamp LocalDateTime from, @Param("to") @CreationTimestamp LocalDateTime to, @Param("vehicle") Vehicle vehicle);

    @Query("select distinct alert.vehicle from Alert alert order by alert.vehicle")
    List<Vehicle> findDistictVehicle();
}
