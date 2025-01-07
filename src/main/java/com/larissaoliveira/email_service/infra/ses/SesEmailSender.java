package com.larissaoliveira.email_service.infra.ses;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.larissaoliveira.email_service.adapters.EmailSenderGateway;
import com.larissaoliveira.email_service.core.exceptions.EmailServiceException;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.print.attribute.standard.Destination;

@Service
public class SesEmailSender implements EmailSenderGateway {


    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService sesClient) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("lah.rio123@gmail.com")
                .withDestination(new Destination().withToAddresses(toEmail))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try {
            this.amazonSimpleEmailService.sendEmail(request);
        }catch (amazonServiceException exception){
            throw new EmailServiceException("Failed to send email");

        }
    }
}
