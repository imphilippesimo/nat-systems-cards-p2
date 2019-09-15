package com.natsystems.cardsp2.service;

import com.natsystems.cardsp2.entity.Card;
import com.natsystems.cardsp2.entity.Category;
import com.natsystems.cardsp2.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardService {
    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card findCard(Long id) {
        return cardRepository.findById(id).orElse(null);

    }

    public Card updateCard(Long id, String label, Category category) {
        Card existingCard = cardRepository.getOne(id);
        if(existingCard!=null){
            existingCard.setCategory(category);
            existingCard.setLabel(label);
        }
        return existingCard;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
