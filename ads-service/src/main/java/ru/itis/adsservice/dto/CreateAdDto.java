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
public class CreateAdDto {
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
    private LocalDate from;
    private LocalDate to;
}