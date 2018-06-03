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

@Rule(name = "Rule Three", description = "Rule: Tire pressure is not below 32psi or above 36psi. Priority: LOW", priority = 3)
public class RuleThree {

    @Condition
    public boolean isAlert(@Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        int frontLeft = reading.getTires().getFrontLeft();
        int frontRight = reading.getTires().getFrontRight();
        int rearLeft = reading.getTires().getRearLeft();
        int rearRight = reading.getTires().getRearRight();
        return (frontLeft < 32 || frontLeft > 36) || (frontRight < 32 || frontRight > 36) || (rearRight < 32 || rearRight > 36) || (rearLeft < 32 || rearLeft > 36);
    }

    @Action
    public void addAlert(@Fact("alertRepository") AlertRepository alertRepository, @Fact("vehicle") Vehicle vehicle, @Fact("reading") Reading reading) {
        alertRepository.save(new Alert("Tire pressure is above/below the limits.", Priority.LOW, vehicle, reading.getTimestamp()));
    }


}
