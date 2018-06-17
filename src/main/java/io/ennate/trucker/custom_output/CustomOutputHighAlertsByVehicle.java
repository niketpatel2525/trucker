package io.ennate.trucker.custom_output;

import java.util.List;
import java.util.Map;

public class CustomOutputHighAlertsByVehicle {
    String vin;
    String priority = "HIGH";
    List<Map<String, Object>> alerts;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<Map<String, Object>> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Map<String, Object>> alerts) {
        this.alerts = alerts;
    }

    public CustomOutputHighAlertsByVehicle() {

    }

    public CustomOutputHighAlertsByVehicle(String vin, List<Map<String, Object>> alerts) {

        this.vin = vin;
        this.alerts = alerts;
    }
}
