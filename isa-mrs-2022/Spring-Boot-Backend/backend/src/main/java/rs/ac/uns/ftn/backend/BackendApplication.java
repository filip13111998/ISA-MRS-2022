package rs.ac.uns.ftn.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import rs.ac.uns.ftn.backend.service.EmailSenderService;

import javax.mail.MessagingException;

@SpringBootApplication

public class BackendApplication {

//	@Autowired
//	private EmailSenderService service;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//
//		service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
//				"This is Email Body with Attachment...",
//				"This email has attachment"
//				);
//
//	}

}
