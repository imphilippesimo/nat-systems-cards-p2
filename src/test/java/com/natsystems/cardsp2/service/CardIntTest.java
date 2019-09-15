package com.natsystems.cardsp2.service;

import com.natsystems.cardsp2.Cardsp2Application;
import com.natsystems.cardsp2.entity.Card;
import com.natsystems.cardsp2.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cardsp2Application.class)
public class CardIntTest {

    @Autowired
    private CardService cardService;

    @Test
    public void should_return_at_least_one_card() {
        List<Card> cards;
        cards = cardService.getCards();
        assertThat(cards.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void should_return_52_cards() {
        List<Card> cards;
        cards = cardService.getCards();
        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    public void should_create() {
        Card card = new Card("As", Category.PIQUE);
        card = cardService.createCard(card);
        Long createdCardId = card.getId();
        assertThat(createdCardId).isNotNull();
        card = cardService.findCard(createdCardId);
        assertThat(card.getLabel()).isEqualTo("As");
        assertThat(card.getCategory()).isEqualTo(Category.PIQUE);
    }

    @Test
    public void should_not_find_a_deleted_card() {
        Card card = cardService.findCard(5L);
        assertThat(card).isNotNull();
        cardService.deleteCard(5L);
        assertThat(cardService.findCard(5L)).isNull();

    }

    @Test
    public void should_find_existing_card() {
        Card card = cardService.findCard(5L);
        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo(5L);
    }

    @Test
    public void should_update() {
        Card card = cardService.updateCard(1L, String.valueOf(10), Category.PIQUE);
        assertThat(card).isNotNull();
        assertThat(card.getCategory()).isEqualTo(Category.PIQUE);
        assertThat(card.getLabel()).isEqualTo(String.valueOf(10));

    }


}
