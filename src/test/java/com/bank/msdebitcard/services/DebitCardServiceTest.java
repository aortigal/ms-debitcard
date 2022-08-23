package com.bank.msdebitcard.services;

import com.bank.msdebitcard.handler.ResponseHandler;
import com.bank.msdebitcard.mock.DebitCardMock;
import com.bank.msdebitcard.models.dao.DebitCardDao;
import com.bank.msdebitcard.models.documents.DebitCard;
import com.bank.msdebitcard.services.impl.DebitCardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class DebitCardServiceTest {

    @InjectMocks
    private DebitCardServiceImpl debitCardService;

    @Mock
    private DebitCardDao debitCardDao;

    @Test
    void findAllTest() {
        DebitCard debitCard= DebitCardMock.random();
        Mockito.when(debitCardDao.findAll()).thenReturn(Flux.empty());
        Mono<ResponseHandler> responseHandlerMono = debitCardService.findAll();
        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void createTest() {

        DebitCard debitCard = DebitCardMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(debitCard);
        Mockito.when(debitCardDao.save(debitCard)).thenReturn(Mono.just(debitCard));
    }

    @Test
    void findTest() {

        DebitCard debitCard = DebitCardMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(debitCard);

        Mockito.when(debitCardDao.findById("62ff27072214b379637d945b"))
                .thenReturn(Mono.just(debitCard));

        Mono<ResponseHandler> responseHandlerMono = debitCardService.find("62ff27072214b379637d945b");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void updateTest() {

        DebitCard debitCard = DebitCardMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(debitCard);

        Mockito.when(debitCardDao.existsById("62ff27072214b379637d945b"))
                .thenReturn(Mono.just(true));

        Mockito.when(debitCardDao.save(debitCard))
                .thenReturn(Mono.just(debitCard));

        Mono<ResponseHandler> responseHandlerMono = debitCardService
                .update("62ff27072214b379637d945b", debitCard);

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void updateFoundTest() {

        DebitCard debitCard = DebitCardMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(debitCard);

        Mockito.when(debitCardDao.existsById("62ff27072214b379637d945b"))
                .thenReturn(Mono.just(false));

        Mono<ResponseHandler> responseHandlerMono = debitCardService
                .update("62ff27072214b379637d945b", debitCard);

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Not found"))
                .verifyComplete();
    }

    @Test
    void deleteTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito.when(debitCardDao.existsById("62ff27072214b379637d945b"))
                .thenReturn(Mono.just(true));

        Mockito.when(debitCardDao.deleteById("62ff27072214b379637d945b")).thenReturn(Mono.empty());

        Mono<ResponseHandler> responseHandlerMono = debitCardService
                .delete("62ff27072214b379637d945b");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Done"))
                .verifyComplete();
    }

    @Test
    void deleteFoundTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito.when(debitCardDao.existsById("62ff27072214b379637d945b"))
                .thenReturn(Mono.just(false));

        Mono<ResponseHandler> responseHandlerMono = debitCardService
                .delete("62ff27072214b379637d945b");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Not found"))
                .verifyComplete();
    }
}
