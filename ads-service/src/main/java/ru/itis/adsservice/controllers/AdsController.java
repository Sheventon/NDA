package ru.itis.adsservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.services.AdsService;
import ru.itis.security.details.UserDetailsImpl;

import java.util.List;

@RestController
@Api(value = "Ad Rest Controller",
        description = "Controller for getting and creating Ads")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @GetMapping("/ads")
    @ApiOperation(value = "Get the list of Ads",
            response = List.class)
    public ResponseEntity<List<AdDto>> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping("ads/create")
    @ApiOperation(value = "Create an Ad",
            response = AdDto.class)
    public ResponseEntity<AdDto> createAd(@RequestPart("ad") String ad,
                                          @RequestPart("files") List<MultipartFile> files,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adsService.createAd(ad, files, userDetails));
    }
}
