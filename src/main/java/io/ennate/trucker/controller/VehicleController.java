package io.ennate.trucker.controller;

import io.ennate.trucker.entity.Alert;
import io.ennate.trucker.custom_output.CustomOutputHighAlertsByVehicle;
import io.ennate.trucker.entity.Vehicle;
import io.ennate.trucker.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://mocker.egen.io/"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/vehicles")
@Api(description = "Vehicle related endpoint")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "create/update vehicles " ,notes = "This endpoint helps to create and/or update vehicle in the database.")
    public List<Vehicle> create(@RequestBody List<Vehicle> vehicles) {
        return vehicleService.create(vehicles);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Returns all vehicle details.", notes = "This endpoint helps to retrieve all details about vehicles from the database.")
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}/alerts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Retrieve all alerts by vehicle")
    public List<Alert> findAllAlertByVehicle(@PathVariable("vin") String vin) {
        System.out.println("Controller");
        return vehicleService.findAllAlertByVehicle(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}/locations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Retrieve Location of vehicle by VIN number.")
    public List<Map<String, String>> findLocationOfAllVehicles(@PathVariable("vin") String vin) {

        return vehicleService.findLocationOfAllVehicles(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all/alerts/high", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Retrieve high alert for all vehicles in the database.")
    public List<CustomOutputHighAlertsByVehicle> findHighAlertForAllVehicle() {
        return vehicleService.findHighAlertForAllVehicle();
    }
}
