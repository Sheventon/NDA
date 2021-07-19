package ru.itis.auctionsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private Long id;
    private String type;
    private Integer area;
    private Integer roomsCount;
    private Integer floor;
}
