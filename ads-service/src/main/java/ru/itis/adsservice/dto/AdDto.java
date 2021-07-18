package ru.itis.adsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.adsservice.models.Ad;
import ru.itis.adsservice.models.Photo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private Long id;
    private String adType;
    private String city;
    private String street;
    private String house;
    private Integer flat;
    private Integer floor;
    private Integer area;
    private Integer roomsCount;
    private String buildingType;
    private String description;
    private String additionalInformation;
    private LocalDate timeFrom;
    private LocalDate timeTo;
    private List<String> photos;
    private UserDto userDto;

    public static AdDto from(Ad ad) {
        return AdDto.builder()
                .id(ad.getId())
                .adType(ad.getType().toString())
                .city(ad.getBuilding().getAddress().getCity())
                .street(ad.getBuilding().getAddress().getStreet())
                .house(ad.getBuilding().getAddress().getHouse())
                .flat(ad.getBuilding().getAddress().getFlat())
                .floor(ad.getBuilding().getFloor())
                .area(ad.getBuilding().getArea())
                .roomsCount(ad.getBuilding().getRoomsCount())
                .buildingType(ad.getBuilding().getBuildingType().toString())
                .description(ad.getDescription())
                .additionalInformation(ad.getAdditionalInformation())
                .timeFrom(ad.getRent().getTimeFrom())
                .timeTo(ad.getRent().getTimeTo())
                .photos(ad.getPhotos().stream().map(Photo::getPath).collect(Collectors.toList()))
                .build();
    }

    public static List<AdDto> from(List<Ad> ads) {
        return ads.stream().map(AdDto::from).collect(Collectors.toList());
    }
}
