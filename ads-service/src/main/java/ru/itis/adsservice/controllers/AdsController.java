package ru.itis.adsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.services.AdsService;

import java.util.List;

@RestController
public class AdsController {

    @Autowired
    private AdsService adsService;

    @GetMapping("/ads")
    public ResponseEntity<List<AdDto>> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping("ads/create")
    public ResponseEntity<AdDto> createAd(@RequestPart("ad") String ad,
                                          @RequestPart("files") List<MultipartFile> files) {
        return ResponseEntity.ok(adsService.createAd(ad, files));
    }
}
