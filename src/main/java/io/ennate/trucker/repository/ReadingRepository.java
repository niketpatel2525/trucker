package io.ennate.trucker.repository;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, String> {

    @Query("SELECT timestamp as Time,longitude as Longitude, latitude as Latitude from Reading r where r.timestamp between :from  and :to and r.vehicle = :vehicle")
    List<Map<String, String>> findLocationByVehicle(@Param("from") @CreationTimestamp LocalDateTime from, @Param("to") @CreationTimestamp LocalDateTime to, @Param("vehicle") Vehicle vehicle);

}
