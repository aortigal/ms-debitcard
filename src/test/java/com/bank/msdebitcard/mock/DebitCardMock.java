package com.bank.msdebitcard.mock;

import com.bank.msdebitcard.models.documents.DebitCard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebitCardMock {

    public static DebitCard random(){
        DebitCard debitCard = new DebitCard();
        debitCard.setId(UUID.randomUUID().toString());
        debitCard.setCardNumber(UUID.randomUUID().toString());
        debitCard.setExpirationDate("10/27");
        debitCard.setClientId(UUID.randomUUID().toString());
        debitCard.setCreatedDate(LocalDateTime.now());
        debitCard.setUpdatedDate(LocalDateTime.now());

        List<String> accounts = new ArrayList<String>();
        accounts.add(UUID.randomUUID().toString());
        accounts.add(UUID.randomUUID().toString());

        debitCard.setAccounts(accounts);

        return debitCard;
    }
}
