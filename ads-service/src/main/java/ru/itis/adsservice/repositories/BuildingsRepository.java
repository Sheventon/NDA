package ru.itis.adsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.adsservice.models.Building;

import java.util.Optional;

public interface BuildingsRepository extends JpaRepository<Building, Long> {

    Optional<Building> findById(Long id);
}
