package ru.itis.adsservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.adsservice.dto.AdDto;
import ru.itis.adsservice.dto.CreateAdDto;
import ru.itis.adsservice.models.*;
import ru.itis.adsservice.repositories.AddressesRepository;
import ru.itis.adsservice.repositories.AdsRepository;
import ru.itis.adsservice.repositories.BuildingsRepository;
import ru.itis.adsservice.repositories.RentsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final String URL_GET_USER = "http://user-service/users/";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private BuildingsRepository buildingsRepository;
    @Autowired
    private RentsRepository rentsRepository;
    @Autowired
    private PhotosService photosService;

    @Override
    public AdDto getById(Long id) {
        return AdDto.from(adsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Ad with id = {" + id + "} not found")));
    }

    @Override
    public AdDto save(Ad ad) {
        return AdDto.from(adsRepository.save(ad));
    }

    @Override
    public List<AdDto> getAllAds() {
        return AdDto.from(adsRepository.findAll());
    }

    @Override
    public AdDto createAd(String strAd, List<MultipartFile> files) {
        CreateAdDto createAdDto = getCreateAdDto(strAd);
        List<Photo> photos = photosService.savePhotos(files);
        Address address = Address.builder()
                .city(createAdDto.getCity())
                .street(createAdDto.getStreet())
                .house(createAdDto.getHouse())
                .flat(createAdDto.getFlat())
                .buildings(new ArrayList<>())
                .build();
        Building building = Building.builder()
                .address(address)
                .buildingType(Building.BuildingType.valueOf(createAdDto.getBuildingType()))
                .area(createAdDto.getArea())
                .floor(createAdDto.getFloor())
                .roomsCount(createAdDto.getRoomsCount())
                .ads(new ArrayList<>())
                .build();
        Rent rent = Rent.builder()
                .timeFrom(createAdDto.getFrom())
                .timeTo(createAdDto.getTo())
                .ads(new ArrayList<>())
                .build();
        Ad ad = Ad.builder()
                .type(Ad.Type.valueOf(createAdDto.getAdType()))
                .additionalInformation(createAdDto.getAdditionalInformation())
                .description(createAdDto.getDescription())
                .building(building)
                .photos(photos)
                .build();
        address.getBuildings().add(building);
        rent.getAds().add(ad);
        building.getAds().add(ad);
        ad.setRent(rent);
        addressesRepository.save(address);
        rentsRepository.save(rent);
        buildingsRepository.save(building);
        adsRepository.save(ad);
        for (Photo photo : photos) {
            photo.setAd(ad);
            photosService.save(photo);
        }
        return AdDto.from(ad);
    }


    @Override
    public CreateAdDto getCreateAdDto(String ad) {
        CreateAdDto createAdDto;
        try {
            createAdDto = objectMapper.readValue(ad, CreateAdDto.class);
        } catch (IOException e) {
            throw new IllegalStateException("Ad information does not found");
        }
        return createAdDto;
    }
}