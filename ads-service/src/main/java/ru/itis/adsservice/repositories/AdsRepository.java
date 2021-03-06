package ru.itis.adsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.adsservice.models.Ad;

import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Long> {

    Optional<Ad> findById(Long id);
}
