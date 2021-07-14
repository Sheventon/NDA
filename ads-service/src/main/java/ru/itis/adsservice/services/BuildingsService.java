package ru.itis.adsservice.services;

import ru.itis.adsservice.models.Building;

public interface BuildingsService {

    Building getById(Long id);

    Long save(Building building);
}
