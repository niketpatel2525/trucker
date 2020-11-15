package io.ennate.trucker.alert.rule;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Priority;
import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.repository.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Rule Four", description = "Rule: EngineCoolant low or checkEngineLightOn. Priority: LOW", priority = 3)

public class RuleFour {
    @Condition
    public boolean isAlert(@Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        return reading.isEngineCoolantLow() || reading.isCheckEngineLightOn();
    }

    @Action
    public void addAlert(@Fact("alertRepository") AlertRepository alertRepository, @Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        alertRepository.save(new Alert("EngineCoolant low or checkEngineLightOn.", Priority.LOW, vehicle, reading.getTimestamp()));
    }


}
