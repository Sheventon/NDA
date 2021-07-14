package ru.itis.adsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.adsservice.models.Address;

import java.util.Optional;

public interface AddressesRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(Long id);
}
