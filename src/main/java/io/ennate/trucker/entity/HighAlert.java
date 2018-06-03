package io.ennate.trucker.entity;

import org.springframework.context.annotation.Bean;

public class HighAlert {
    Vehicle vehicle;
    Alert[] alerts;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Alert[] getAlerts() {
        return alerts;
    }

    public void setAlerts(Alert[] alerts) {
        this.alerts = alerts;
    }

    public HighAlert() {

    }

    public HighAlert(Vehicle vehicle, Alert[] alerts) {
        this.vehicle = vehicle;
        this.alerts = alerts;
    }
}
