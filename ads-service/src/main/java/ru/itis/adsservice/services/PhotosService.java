package ru.itis.adsservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.models.Photo;

import java.util.List;

public interface PhotosService {
    
    Photo getById(Long id);
    
    Long save(Photo photo);

    List<Photo> savePhotos(List<MultipartFile> files);
}
