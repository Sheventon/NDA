package ru.itis.auctionsservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adId;
    private Long ownerId; //user_id
    private Integer buyNowValue;
    private Integer currentValue;
    private Boolean isClosed;

    @ToString.Exclude
    @OneToMany(mappedBy = "auction")
    private List<Rate> rates;
}
