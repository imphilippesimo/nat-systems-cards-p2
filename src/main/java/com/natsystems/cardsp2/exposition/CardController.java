package com.natsystems.cardsp2.exposition;

import com.natsystems.cardsp2.entity.Card;
import com.natsystems.cardsp2.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/cards")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(cardService.getCards());

    }


}
