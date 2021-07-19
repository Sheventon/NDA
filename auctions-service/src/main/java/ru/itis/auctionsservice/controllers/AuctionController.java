package ru.itis.auctionsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.auctionsservice.models.Auction;
import ru.itis.auctionsservice.services.AuctionService;

@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/auction/{id}")
    public ResponseEntity<Auction> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(auctionService.getById(id));
    }
}
