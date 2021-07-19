package ru.itis.auctionsservice.services;

import ru.itis.auctionsservice.dto.AdDto;
import ru.itis.auctionsservice.models.Auction;
import ru.itis.auctionsservice.models.Rate;
import ru.itis.security.details.UserDetailsImpl;

public interface AuctionService {
    Auction getById(Long id);
    AdDto getInformationAboutAd(Long adId, UserDetailsImpl userDetails);
    Auction startAuction(Long adId, Integer finalCost, UserDetailsImpl userDetails);
    Rate makeRate(Long auctionId, Integer value, UserDetailsImpl userDetails);
    Boolean endAuction(Long auctionId, UserDetailsImpl userDetails);
}
