package ru.itis.adsservice.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 5000)
    private String description;

    @Length(max = 5000)
    private String additionalInformation;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "rent_id", referencedColumnName = "id")
    private Rent rent;

    @ToString.Exclude
    @OneToMany(mappedBy = "ad")
    private List<Photo> photos;

    private Long userId;

    public enum Type {
        BUY, BOOK
    }
}
