package ru.itis.auctionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionsservice.models.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
