package com.bank.msdebitcard.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "debitcards")
public class DebitCard {

    @Id
    private String id;
    private String cardNumber;
    private String expirationDate;
    @NotNull(message = "clientId must not be null")
    private String clientId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "GMT-05:00")
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<String> accounts;
}
