package pl.akai.bookcrossing.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.user.database.UserEntity;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    public UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return UserEntity.builder()
                         .id(Integer.parseInt(jwt.getClaim("id").toString()))
                         .email(jwt.getClaim("email"))
                         .fullName(jwt.getClaim("fullName"))
                         .build();
    }
}
