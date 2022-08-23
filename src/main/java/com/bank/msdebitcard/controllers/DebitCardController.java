package com.bank.msdebitcard.controllers;

import com.bank.msdebitcard.handler.ResponseHandler;
import com.bank.msdebitcard.models.documents.DebitCard;
import com.bank.msdebitcard.services.DebitCardService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/debitcard")
public class DebitCardController {

    @Autowired
    private DebitCardService debitCardService;

    @PostMapping
    public Mono<ResponseHandler> create(@Valid @RequestBody DebitCard d) {
        return debitCardService.create(d);
    }

    @GetMapping
    public Mono<ResponseHandler> findAll() {
        return debitCardService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseHandler> find(@PathVariable String id) {
        return debitCardService.find(id);
    }


    @PutMapping("/{id}")
    public Mono<ResponseHandler> update(@PathVariable("id") String id,@Valid @RequestBody DebitCard d) {
        return debitCardService.update(id, d);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseHandler> delete(@PathVariable("id") String id) {
        return debitCardService.delete(id);
    }

}
