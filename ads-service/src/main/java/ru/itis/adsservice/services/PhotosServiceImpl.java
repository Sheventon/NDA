package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.models.Photo;
import ru.itis.adsservice.repositories.PhotosRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotosServiceImpl implements PhotosService {

    @Value("${file.upload-dir}")
    private String FILE_DIRECTORY;

    private String PATH = "http://MEDIA-SERVICE/media/photo/save";

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private RestTemplateService templateService;

    @Override
    public Photo getById(Long id) {
        return photosRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Photo with id = {" + id + "} not found"));
    }

    @Override
    public Long save(Photo photo) {
        return photosRepository.save(photo).getId();
    }

    @Override
    public List<Photo> savePhotos(List<MultipartFile> files) {
        List<Photo> photos = new ArrayList<>();
        for (MultipartFile file : files) {
            photos.add(Photo.builder()
                    .path(String.valueOf(templateService.saveFile(PATH + FILE_DIRECTORY, file)))
                    .build());
        }
        return photos;
    }
}
