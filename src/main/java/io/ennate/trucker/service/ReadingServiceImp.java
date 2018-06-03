package io.ennate.trucker.service;

import io.ennate.trucker.alert.rule.RuleFour;
import io.ennate.trucker.alert.rule.RuleOne;
import io.ennate.trucker.alert.rule.RuleThree;
import io.ennate.trucker.alert.rule.RuleTwo;
import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.exception.VehicleNotFoundException;
import io.ennate.trucker.repository.AlertRepository;
import io.ennate.trucker.repository.ReadingRepository;
import io.ennate.trucker.repository.VehicleRepository;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
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

    @Autowired
    private AlertRepository aRepository;

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
