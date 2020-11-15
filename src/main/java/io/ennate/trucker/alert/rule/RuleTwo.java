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

@Rule(name = "Rule two", description = "Rule: Fuel volume is less than 10% of max fuel capacity. Priority: MEDIUM", priority = 2)
public class RuleTwo {

    @Condition
    public boolean isAlert(@Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        return (reading.getFuelVolume() < (0.10 * vehicle.getMaxFuelVolume()));
    }

    @Action
    public void addAlert(@Fact("alertRepository") AlertRepository alertRepository, @Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        alertRepository.save(new Alert("Fuel volume is less than 10% of max fuel capacity.", Priority.MEDIUM, vehicle, reading.getTimestamp()));
    }
}
