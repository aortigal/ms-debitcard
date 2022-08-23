package com.bank.msdebitcard.services.impl;

import com.bank.msdebitcard.handler.ResponseHandler;
import com.bank.msdebitcard.models.dao.DebitCardDao;
import com.bank.msdebitcard.models.documents.DebitCard;
import com.bank.msdebitcard.services.DebitCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private DebitCardDao dao;

    private static final Logger log = LoggerFactory.getLogger(DebitCardServiceImpl.class);

    @Override
    public Mono<ResponseHandler> create(DebitCard d) {
        log.info("[INI] Create Debit Card");
        d.setCreatedDate(LocalDateTime.now());
        return dao.save(d)
                .doOnNext(debitCard -> log.info(debitCard.toString()))
                .map(debitCard -> new ResponseHandler("Done", HttpStatus.OK, debitCard))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] Create Debit Card"));
    }

    @Override
    public Mono<ResponseHandler> findAll() {
        log.info("[INI] FindAll Debit Card");

        return dao.findAll()
                .doOnNext(debitCard -> log.info(debitCard.toString()))
                .collectList().map(debitCards -> new ResponseHandler("Done", HttpStatus.OK, debitCards))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .switchIfEmpty(Mono.just(new ResponseHandler("Empty", HttpStatus.NO_CONTENT, null)))
                .doFinally(fin -> log.info("[END] FindAll Debit Crd"));

    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        log.info("[INI] Find Debit Card");
        return dao.findById(id)
                .doOnNext(debitCard -> log.info(debitCard.toString()))
                .map(debitCard -> new ResponseHandler("Done", HttpStatus.OK, debitCard))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .switchIfEmpty(Mono.just(new ResponseHandler("Empty", HttpStatus.NO_CONTENT, null)))
                .doFinally(fin -> log.info("[END] Find Debit Card"));
    }

    @Override
    public Mono<ResponseHandler> update(String id, DebitCard d) {
        log.info("[INI] update Debit Card");
        return dao.existsById(id).flatMap(check -> {
                    if (check){
                        d.setUpdatedDate(LocalDateTime.now());
                        return dao.save(d)
                                .doOnNext(pasive -> log.info(pasive.toString()))
                                .map(pasive -> new ResponseHandler("Done", HttpStatus.OK, pasive))
                                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
                    }
                    else
                        return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));

                })
                .doFinally(fin -> log.info("[END] Update Debit Card"));
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        log.info("[INI] delete Debit Card");
        return dao.existsById(id).flatMap(check -> {
                    if (check)
                        return dao.deleteById(id).then(Mono.just(new ResponseHandler("Done", HttpStatus.OK, null)));
                    else
                        return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
                })
                .doFinally(fin -> log.info("[END] Delete Debit Card"));
    }


}
