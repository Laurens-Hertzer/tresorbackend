package ch.bbw.pr.tresorbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class RecaptchaVerificationService {

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final Logger logger = LoggerFactory.getLogger(RecaptchaVerificationService.class);

    private final RestClient restClient;
    private final String secret;

    public RecaptchaVerificationService(RestClient.Builder restClientBuilder,
                                       @Value("${RECAPTCHA_SECRET}") String secret) {
        this.restClient = restClientBuilder.build();
        this.secret = secret;
    }

    public boolean verify(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secret);
        form.add("response", token);

        JsonNode response = restClient.post()
                .uri(VERIFY_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(JsonNode.class);

        if (response == null) {
            logger.warn("reCAPTCHA verification returned an empty response");
            return false;
        }

        boolean success = response.path("success").asBoolean(false);
        if (!success) {
            logger.warn("reCAPTCHA verification rejected: {}", response.path("error-codes"));
        }
        return success;
    }
}
