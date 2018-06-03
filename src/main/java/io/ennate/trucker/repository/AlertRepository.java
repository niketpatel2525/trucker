package io.ennate.trucker.repository;

import io.ennate.trucker.entity.Alert;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository extends CrudRepository<Alert, String> {
}
