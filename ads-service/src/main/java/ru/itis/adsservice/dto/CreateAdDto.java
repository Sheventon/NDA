package ru.itis.adsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private String adType;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
    private Integer floor;
    private Integer area;
    private Integer roomsCount;
    private String buildingType;
    private String description;
    private String additionalInformation;
    private LocalDate from;
    private LocalDate to;
}

/*
    Ad:                             Building:                           Address:
        - name = address                - area                              - city
        - ad type                       - rooms count                       - street
        - description                   - floor                             - house
        - addit inf                     - build type                        - flat
        - building id                   - address

       from front:
           1 - ad type
           2 - Address
           3 - Building
           4 - photos
           5 - descr
           6 - addit inf
           7 - rent (from, to)

 */
