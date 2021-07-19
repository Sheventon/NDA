package ru.itis.auctionsservice.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;
    private LocalDateTime rateTime = LocalDateTime.now();

    private Long userId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "rates")
    private Auction auction;

}
