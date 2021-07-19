package ru.itis.auctionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionsservice.models.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
