package ru.itis.adsservice.services;

import ru.itis.adsservice.models.Address;

public interface AddressesService {

    Address getById(Long id);

    Long save(Address address);
}
