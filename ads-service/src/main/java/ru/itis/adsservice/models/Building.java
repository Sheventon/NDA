package ru.itis.adsservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer area;

    private Integer roomsCount;

    private Integer floor;

    @Enumerated(value = EnumType.STRING)
    private BuildingType buildingType;

    @ToString.Exclude
    @OneToMany(mappedBy = "building")
    private List<Ad> ads;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public enum BuildingType {
        APARTMENT, PRIVATE_HOUSE
    }
}
