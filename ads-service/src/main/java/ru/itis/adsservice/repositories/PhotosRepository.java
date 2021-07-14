package ru.itis.adsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.adsservice.models.Photo;

import java.util.Optional;

public interface PhotosRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findById(Long id);
}
