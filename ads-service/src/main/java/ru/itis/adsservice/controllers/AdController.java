package ru.itis.adsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.services.AdsService;

@RestController
public class AdController {

    @Autowired
    private AdsService adsService;

    @GetMapping("/ads/ad/{id}")
    public ResponseEntity<AdDto> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adsService.getById(id));
    }
}
