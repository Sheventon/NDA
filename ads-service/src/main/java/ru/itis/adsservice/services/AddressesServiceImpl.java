package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.adsservice.models.Address;
import ru.itis.adsservice.repositories.AddressesRepository;

@Service
public class AddressesServiceImpl implements AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Address getById(Long id) {
        return addressesRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Address with id = {" + id + "} not found"));
    }

    @Override
    public Long save(Address address) {
        return addressesRepository.save(address).getId();
    }
}
