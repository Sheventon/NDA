package ru.itis.adsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.models.Photo;
import ru.itis.adsservice.repositories.PhotosRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PhotosServiceImpl implements PhotosService {

    @Value("${file.upload-dir}")
    private String FILE_DIRECTORY;

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

    @Override
    public List<Photo> savePhotos(List<MultipartFile> files) {
        List<Photo> photos = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String path = FILE_DIRECTORY + UUID.randomUUID() + ".jpg";
            File file = new File(path);
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.close();
                Photo photo = Photo.builder()
                        .path(path)
                        .build();
                photos.add(photo);
            } catch (IOException e) {
                throw new IllegalStateException("File could not be created");
            }
        }
        return photos;
    }
}
