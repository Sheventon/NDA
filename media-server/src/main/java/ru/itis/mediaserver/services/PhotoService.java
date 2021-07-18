package ru.itis.mediaserver.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    Long savePhoto(MultipartFile photo, String folder);
    byte[] getPhotoById(Long id);
}
