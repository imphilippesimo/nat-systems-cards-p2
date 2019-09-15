package com.natsystems.cardsp2;

import com.natsystems.cardsp2.entity.Card;
import com.natsystems.cardsp2.entity.Category;
import com.natsystems.cardsp2.service.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Cardsp2Application {

    private CardService cardService;

    public Cardsp2Application(CardService cardService) {
        this.cardService = cardService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Cardsp2Application.class, args);
    }


    @PostConstruct
    public void constructCards() {
        if (cardService.getCards().size() == 0) {
            String label;
            for (Category cat : Category.values()) {
                for (int i = 1; i <= 13; i++) {
                    if (i == 1) {
                        label = "As";
                    } else if (i == 11) {
                        label = "Valet";
                    } else if (i == 12) {
                        label = "Reine";
                    } else if (i == 13) {
                        label = "Roi";
                    } else {
                        label = String.valueOf(i);
                    }
                    cardService.createCard(new Card(label, cat));
                }
            }

        }

    }

}
