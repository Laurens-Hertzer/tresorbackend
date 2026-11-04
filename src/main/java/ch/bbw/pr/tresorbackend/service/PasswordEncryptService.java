package ch.bbw.pr.tresorbackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * PasswordEncryptService
 *   used to hash password and verify match
 * @author Peter Rutschmann
 */
@Service
public class PasswordEncryptService {

    //Pepper wird aus application.properties gelesen
    @Value("${PEPPER}")
    private String pepper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public PasswordEncryptService() {
        //todo add implementation here
    }

    public String hashPassword(String password) {
        return encoder.encode(password + pepper);
    }

    public boolean doPasswordMatch(String password, String hashedPassword) {
        return encoder.matches(password+pepper, hashedPassword);
    }
}