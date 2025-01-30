package ProjetDeSyntheseNotification.notification.Validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    // Regex pour valider les emails
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Vérifie si l'email et le message sont valides
     */
    public String validateEmail(String email, String message) {
        if (email == null || email.isEmpty()) {
            return "L'adresse email est obligatoire.";
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "L'adresse email est invalide.";
        }

        if (message == null || message.trim().isEmpty()) {
            return "Le message ne peut pas être vide.";
        }

        return null; // Tout est valide
    }
}
