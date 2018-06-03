package io.ennate.trucker.service;

import io.ennate.trucker.alert.rule.RuleFour;
import io.ennate.trucker.alert.rule.RuleOne;
import io.ennate.trucker.alert.rule.RuleThree;
import io.ennate.trucker.alert.rule.RuleTwo;
import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.exception.VehicleNotFoundException;
import io.ennate.trucker.repository.AlertRepository;
import io.ennate.trucker.repository.VehicleRepository;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertServiceImp implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private VehicleRepository vRepository;

    @Override
    public void checkForAlert(Reading reading) {
        Optional<Vehicle> vehicleExist = vRepository.findByVin(reading.getVehicle().getVin());
        if (!vehicleExist.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with VIN:#" + reading.getVehicle().getVin() + " not available.");
        }
        reading.setVehicle(vehicleExist.get());

//        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstFailedRule(true).skipOnFirstNonTriggeredRule(true);
        RulesEngineParameters parameters = new RulesEngineParameters();
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        Rules rules = new Rules();
        rules.register(new RuleOne());
        rules.register(new RuleTwo());
        rules.register(new RuleThree());
        rules.register(new RuleFour());

        Facts facts = new Facts();
        facts.put("vehicle", vehicleExist.get());
        facts.put("reading", reading);
        facts.put("alertRepository", alertRepository);
        rulesEngine.fire(rules, facts);
    }
}
