package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.adsservice.models.Photo;
import ru.itis.adsservice.repositories.PhotosRepository;

@Service
public class PhotosServiceImpl implements PhotosService {

    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public Photo getById(Long id) {
        return photosRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Photo with id = {" + id + "} not found"));
    }

    @Override
    public Long save(Photo photo) {
        return photosRepository.save(photo).getId();
    }
}
