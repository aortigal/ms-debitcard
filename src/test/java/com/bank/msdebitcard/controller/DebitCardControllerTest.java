package com.bank.msdebitcard.controller;

import com.bank.msdebitcard.handler.ResponseHandler;
import com.bank.msdebitcard.controllers.DebitCardController;
import com.bank.msdebitcard.services.DebitCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.HttpStatus;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.lang.management.MonitorInfo;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(DebitCardController.class)
public class DebitCardControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private DebitCardService debitCardService;

    @Test
    void findAllTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);
        when(debitCardService.findAll()).thenReturn(Mono.just(responseHandler));

        webClient
                .get().uri("/api/debitcard")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ResponseHandler.class);

    }

}
