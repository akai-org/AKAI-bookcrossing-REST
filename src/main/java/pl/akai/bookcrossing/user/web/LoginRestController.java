package pl.akai.bookcrossing.user.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akai.bookcrossing.user.dto.LoginDto;
import pl.akai.bookcrossing.user.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto tokenId) {
        LoginDto signedToken = loginService.authenticateUser(tokenId);
        return new ResponseEntity<>(signedToken, HttpStatus.OK);
    }

}
