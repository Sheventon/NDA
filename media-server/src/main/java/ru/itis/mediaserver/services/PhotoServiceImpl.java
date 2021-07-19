package ru.itis.mediaserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mediaserver.models.Photo;
import ru.itis.mediaserver.repositories.PhotoRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Value("${file.upload-dir}")
    private String FILE_DIRECTORY;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Long savePhoto(MultipartFile photo, String folder) {
        String path = FILE_DIRECTORY + folder + "/";
        try {
            Files.createDirectories(Paths.get(path));
            path = path + UUID.randomUUID().toString() + ".jpg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(photo.getBytes());
            fileOutputStream.close();
            Photo saved = photoRepository.save(Photo.builder()
                    .path(path)
                    .build());
            return saved.getId();
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be created");
        }
    }

    @Override
    public byte[] getPhotoById(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow();
            try {
                InputStream in = new FileInputStream(photo.getPath());
                return in.readAllBytes();
            } catch (IOException e) {
                throw new IllegalStateException("Photo with id = {" + id + "} not found");
            }
        }
    }
