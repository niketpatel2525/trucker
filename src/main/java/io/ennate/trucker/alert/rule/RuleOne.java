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

@Rule(name = "Rule one", description = "Rule: engineRpm > readlineRpm, Priority: HIGH", priority = 1)
public class RuleOne {

    @Condition
    public boolean isAlert(@Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        return (reading.getEngineRpm() > vehicle.getReadlineRpm());
    }

    @Action
    public void addAlert(@Fact("alertRepository") AlertRepository alertRepository, @Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        alertRepository.save(new Alert("Engine RPM is greater than read line RPM.", Priority.HIGH, vehicle, reading.getTimestamp()));
    }

}
