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

    @Value("${file.upload-dir.users}")
    private String FILE_DIRECTORY_USERS;

    @Value("${file.upload-dir.ads}")
    private String FILE_DIRECTORY_ADS;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public String savePhoto(MultipartFile photo, Boolean isFromUser, Long id) {
        String path = "";
        if (isFromUser) {
            path = path + FILE_DIRECTORY_USERS;
        } else {
            path = path + FILE_DIRECTORY_ADS;
        }
        path = path + "id" + id + "/";
        try {
            Files.createDirectories(Paths.get(path));
            path = path + UUID.randomUUID().toString() + ".jpg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(photo.getBytes());
            fileOutputStream.close();
            photoRepository.save(Photo.builder()
                    .path(path)
                    .build());
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be created");
        }
        return path;
    }

    @Override
    public byte[] getPhotoById(Long id) {
        Photo photo = photoRepository.getById(id);
            try {
                InputStream in = new FileInputStream(new File(photo.getPath()));
                byte[] media = in.readAllBytes();
                return media;
            } catch (IOException e) {
                throw new IllegalStateException("Photo with id = {" + id + "} not found");
            }
        }
    }
