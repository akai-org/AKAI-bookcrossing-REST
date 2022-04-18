package pl.akai.bookcrossing.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.common.service.InvalidTokenException;
import pl.akai.bookcrossing.user.database.UserEntity;
import pl.akai.bookcrossing.user.dto.LoginDto;

@Service
@RequiredArgsConstructor
public class GoogleTokenIdValidator {

    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    public UserEntity validate(LoginDto studentLoginDTO) {
        try {
            GoogleIdToken idToken = googleIdTokenVerifier.verify(studentLoginDTO.getToken());
            GoogleIdToken.Payload payload = idToken.getPayload();
            return UserEntity.builder()
                                .email(payload.getEmail())
                                .fullName((String) payload.get("name"))
                                .build();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
