package ru.itis.adsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.adsservice.models.Rent;

import java.util.Optional;

public interface RentsRepository extends JpaRepository<Rent, Long> {

    Optional<Rent> findById(Long id);
}
