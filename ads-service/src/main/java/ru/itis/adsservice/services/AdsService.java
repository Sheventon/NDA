package ru.itis.adsservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.dto.CreateAdDto;
import ru.itis.adsservice.models.Ad;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.models.Photo;

import java.util.List;

public interface AdsService {

    AdDto getById(Long id);

    AdDto save(Ad ad);

    List<AdDto> getAllAds();

    AdDto createAd(String strAd, List<MultipartFile> files);

    List<Photo> savePhotos(List<MultipartFile> files);

    CreateAdDto getCreateAdDto(String ad);
}
