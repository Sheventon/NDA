package ru.itis.adsservice.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Ads creation required ad data")
public class CreateAdDto {

    @NotNull
    @ApiModelProperty(value = "The type of an Ad",
            example = "book")
    private String adType;

    @NotNull
    @ApiModelProperty(value = "The city of an object from ad",
            example = "Kazan")
    private String city;

    @NotNull
    @ApiModelProperty(value = "The street where the object is located",
            example = "Kremlevskaya")
    private String street;

    @NotNull
    @ApiModelProperty(value = "The number of house in the street",
            example = "35")
    private String house;

    @ApiModelProperty(value = "The number of flat if exists",
            example = "1309")
    private Integer flat;

    @NotNull
    @ApiModelProperty(value = "If it's a house, the number of floors. If it's a flat, the floot where this flat is located",
            example = "13")
    private Integer floor;

    @NotNull
    @ApiModelProperty(value = "The area of an object",
            example = "350")
    private Integer area;

    @NotNull
    @ApiModelProperty(value = "The count of rooms",
            example = "3")
    private Integer roomsCount;

    @NotNull
    @ApiModelProperty(value = "The type of an object",
            example = "house")
    private String buildingType;

    @NotNull
    @Size(max = 255)
    @ApiModelProperty(value = "Description of on object",
            example = "Apartment on the sunny side")
    private String description;

    @Size(max = 1024)
    @ApiModelProperty(value = "Additional information about in object",
            example = "You can't have animals here")
    private String additionalInformation;

    @ApiModelProperty(value = "Used for rents, shows the first date of rent",
            example = "19.06.2021")
    private LocalDate from;

    @ApiModelProperty(value = "Used for rents, shows the last date of rent",
            example = "19.07.2021")
    private LocalDate to;
}
