package io.ennate.trucker.controller;

import io.ennate.trucker.entity.Reading;
import io.ennate.trucker.service.AlertService;
import io.ennate.trucker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://mocker.egen.io/"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/readings")
public class ReadingController {
    @Autowired
    private ReadingService service;
    @Autowired
    private AlertService alertService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody Reading reading) {
        alertService.checkForAlert(reading);
        service.create(reading);
    }
}
