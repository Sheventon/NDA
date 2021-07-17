package ru.itis.mediaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mediaserver.services.PhotoService;

@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo/save")
    public ResponseEntity<String> postPhoto(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(photoService.savePhoto(file, true, 1L));
    }

    @GetMapping(value = "/photo/id{photo-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable("photo-id") Long id) {
        return ResponseEntity.ok(photoService.getPhotoById(id));
    }
}
