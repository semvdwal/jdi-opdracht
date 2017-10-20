package library;

import models.User;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;

public class UserMailer {

    public static void sendUserCreateMail(User user) {
        Email email = new EmailBuilder()
                .from("Sem-IT Noreply", "noreply@sem-it.nl")
                .to(user.getFullName(), user.getEmail())
                .subject("Welkom bij JDI!")
                .text(
                        "Welkom " + user.getFullName() + "!\n\n" +
                        "We hebben een gebruiker voor je geregistreerd met de gebruikersnaam " + user.getUsername()
                )
                .build();

        new Mailer(
            "smtp.gmail.com",
            465,
            "noreply@sem-it.nl",
            "775BB1t3Au",
            TransportStrategy.SMTP_SSL
        ).sendMail(email);
    }

}
