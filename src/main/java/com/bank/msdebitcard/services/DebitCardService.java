package com.bank.msdebitcard.services;

import com.bank.msdebitcard.handler.ResponseHandler;
import com.bank.msdebitcard.models.documents.DebitCard;
import reactor.core.publisher.Mono;

public interface DebitCardService {

    Mono<ResponseHandler> create(DebitCard d);

    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> update(String id, DebitCard p);

    Mono<ResponseHandler> delete(String id);

}
