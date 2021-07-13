package ru.itis.adsservice.services;

import ru.itis.adsservice.models.Photo;

public interface PhotosService {
    
    Photo getById(Long id);
    
    Long save(Photo photo);
}
