package ru.itis.adsservice.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Renta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime from;

    private LocalDateTime to;

    @ToString.Exclude
    @OneToMany(mappedBy = "rent")
    private List<Ad> ads;
}
