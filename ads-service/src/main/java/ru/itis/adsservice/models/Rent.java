package ru.itis.adsservice.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate timeFrom;

    private LocalDate timeTo;

    @ToString.Exclude
    @OneToMany(mappedBy = "rent")
    private List<Ad> ads;
}
