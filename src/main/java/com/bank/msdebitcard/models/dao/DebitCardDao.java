package com.bank.msdebitcard.models.dao;

import com.bank.msdebitcard.models.documents.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardDao extends ReactiveMongoRepository<DebitCard, String> {
}
