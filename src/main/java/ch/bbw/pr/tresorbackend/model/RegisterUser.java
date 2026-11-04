package ch.bbw.pr.tresorbackend.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * RegisterUser
 * @author Peter Rutschmann
 */
@Value
public class RegisterUser {

   @NotEmpty(message="Firstname is required.")
   @Size(min=2, max=25, message="Firstname size has to be 2 up to 25 characters.")
   private String firstName;

   @NotEmpty (message="Lastname is required.")
   @Size(min=2, max=25, message="Lastname size has to be 2 up to 25 characters.")
   private String lastName;

   @NotEmpty (message="E-Mail is required.")
   private String email;

   @NotEmpty (message="Password is required.")
   @Size(min=12, max=72, message="Password must be 12 to 72 characters long.")
   @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9])[!-~]+$", message="Password must contain an uppercase letter, a lowercase letter, a number, and a special character; spaces are not allowed.")
   private String password;

   @NotEmpty (message="Password-confirmation is required.")
   private String passwordConfirmation;

   @JsonAlias("captchaToken")
   private String recaptchaToken;
}