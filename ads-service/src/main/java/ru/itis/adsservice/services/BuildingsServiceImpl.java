package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.adsservice.models.Building;
import ru.itis.adsservice.repositories.BuildingsRepository;

@Service
public class BuildingsServiceImpl implements BuildingsService {

    @Autowired
    private BuildingsRepository buildingsRepository;

    @Override
    public Building getById(Long id) {
        return buildingsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Building with id = {" + id + "} not found"));
    }

    @Override
    public Long save(Building building) {
        return buildingsRepository.save(building).getId();
    }
}
