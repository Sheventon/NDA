package ru.itis.mediaserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.mediaserver.models.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
