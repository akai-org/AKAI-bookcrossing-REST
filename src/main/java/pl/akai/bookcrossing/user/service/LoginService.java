package pl.akai.bookcrossing.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.security.GoogleTokenIdValidator;
import pl.akai.bookcrossing.security.JwtBuilder;
import pl.akai.bookcrossing.user.database.UserEntity;
import pl.akai.bookcrossing.user.database.UserRepository;
import pl.akai.bookcrossing.user.dto.LoginDto;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final GoogleTokenIdValidator googleTokenIdValidator;
    private final UserRepository userRepository;
    private final JwtBuilder jwtBuilder;

    public LoginDto authenticateUser(LoginDto tokenId) {
        UserEntity user = googleTokenIdValidator.validate(tokenId);
        UserEntity userWithId = userRepository.findByEmail(user.getEmail())
                                              .orElseGet(() -> userRepository.save(user));
        String token = jwtBuilder.getToken(userWithId.getEmail(), getClaims(userWithId));
        return new LoginDto(token);
    }

    private Map<String, Object> getClaims(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        return claims;
    }
}
