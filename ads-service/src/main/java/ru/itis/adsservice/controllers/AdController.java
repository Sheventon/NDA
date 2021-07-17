package ru.itis.adsservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.services.AdsService;

@RestController
@Api(value = "Ad Rest Controller",
        description = "Controller for getting Ad by id")
public class AdController {

    @Autowired
    private AdsService adsService;

    @GetMapping("/ads/ad/{id}")
    @ApiOperation(value = "Get the Ad",
            response = AdDto.class)
    public ResponseEntity<AdDto> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adsService.getById(id));
    }
}
