package ru.itis.adsservice.services;

import ru.itis.adsservice.models.Rent;

public interface RentsService {

    Rent getById(Long id);

    Long save(Rent rent);
}
