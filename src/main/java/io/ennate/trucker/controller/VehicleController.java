package io.ennate.trucker.controller;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://mocker.egen.io/"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> create(@RequestBody List<Vehicle> vehicles) {
        return vehicleService.create(vehicles);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}/alerts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAllAlertByVehicle(@PathVariable("vin") String vin) {
        System.out.println("Controller");
        return vehicleService.findAllAlertByVehicle(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}/locations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Map<String, String>> findLocationOfAllVehicles(@PathVariable("vin") String vin) {

        return vehicleService.findLocationOfAllVehicles(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all/alerts/high", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findHighAlertForAllVehicle() {
        return vehicleService.findHighAlertForAllVehicle();
    }
}
