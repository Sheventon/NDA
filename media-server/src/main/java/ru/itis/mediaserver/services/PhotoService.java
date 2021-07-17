package ru.itis.mediaserver.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    Long savePhoto(MultipartFile photo, Boolean isFromUser, Long id);
    byte[] getPhotoById(Long id);
}
