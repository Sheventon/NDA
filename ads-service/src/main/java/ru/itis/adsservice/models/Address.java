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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private String house;

    private Integer flat;

    @ToString.Exclude
    @OneToMany(mappedBy = "address")
    private List<Building> buildings;
}
