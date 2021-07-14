package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.adsservice.models.Rent;
import ru.itis.adsservice.repositories.RentsRepository;

@Service
public class RentsServiceImpl implements RentsService {

    @Autowired
    private RentsRepository rentsRepository;

    @Override
    public Rent getById(Long id) {
        return rentsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Rent with id = {" + id + "} not found"));
    }

    @Override
    public Long save(Rent rent) {
        return rentsRepository.save(rent).getId();
    }
}
