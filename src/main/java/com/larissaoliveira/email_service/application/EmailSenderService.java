package com.larissaoliveira.email_service.application;

import com.larissaoliveira.email_service.adapters.EmailSenderGateway;
import com.larissaoliveira.email_service.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSenderService implements EmailSenderUseCase {

private final EmailSenderGateway emailSenderGateway;

@Autowired
public EmailSenderService(EmailSenderGateway emailGateway) {
        this.emailSenderGateway = emailGateway;
    }

    public void sendEmail(String to, String subject, String body) {
    this.emailSenderGateway.sendEmail(to, subject, body);
}
}
