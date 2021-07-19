package ru.itis.auctionsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.auctionsservice.dto.AdDto;
import ru.itis.auctionsservice.models.Auction;
import ru.itis.auctionsservice.models.Rate;
import ru.itis.auctionsservice.repositories.AuctionRepository;
import ru.itis.auctionsservice.repositories.RateRepository;
import ru.itis.security.details.UserDetailsImpl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Auction getById(Long id) {
        return auctionRepository.getById(id);
    }

    @Override
    public AdDto getInformationAboutAd(Long adId, UserDetailsImpl userDetails) {
        String request = "http://ads-service/ads/ad/" + adId;
        RequestEntity<?> requestEntity = RequestEntity.get(URI.create(request))
                .header("Authorization", userDetails.getToken())
                .build();
        ResponseEntity<AdDto> ad = restTemplate.exchange(requestEntity, AdDto.class);
        return ad.getBody();
    }

    @Override
    public Auction startAuction(Long adId, Integer finalCost, UserDetailsImpl userDetails) {
        AdDto ad = getInformationAboutAd(adId, userDetails);
        return auctionRepository.save(Auction
                .builder()
                .adId(ad.getId())
                .currentValue(0)
                .buyNowValue(finalCost)
                .isClosed(false)
                .ownerId(userDetails.getId())
                .rates(new ArrayList<>())
                .build());
    }

    @Override
    public Rate makeRate(Long auctionId, Integer value, UserDetailsImpl userDetails) {
        Auction auction = auctionRepository.getById(auctionId);
        //Rate lastRate = auction.getRates().get(auction.getRates().size() - 1);
        auction.setCurrentValue(auction.getCurrentValue() + value);
        auctionRepository.save(auction);
        Rate newRate = Rate.builder()
                .auction(auction)
                .rateTime(LocalDateTime.now())
                .value(value)
                .userId(userDetails.getId())
                .build();
        rateRepository.save(newRate);
        auction.getRates().add(newRate);
        return newRate;
    }

    @Override
    public Boolean endAuction(Long auctionId, UserDetailsImpl userDetails) {
        Auction auction = auctionRepository.getById(auctionId);
        auction.setIsClosed(true);
        auctionRepository.save(auction);
        return true;
    }

}
