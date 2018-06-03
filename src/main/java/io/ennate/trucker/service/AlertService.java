package io.ennate.trucker.service;

import io.ennate.trucker.entity.Reading;

public interface AlertService {

    public void checkForAlert(Reading reading);
}
