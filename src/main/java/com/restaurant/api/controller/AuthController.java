package com.restaurant.api.controller;

import com.restaurant.api.domain.person.dto.AuthRequestDTO;
import com.restaurant.api.domain.user.AuthUserDetails;
import com.restaurant.api.infra.security.TokenDTO;
import com.restaurant.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthRequestDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var userDetails = (AuthUserDetails) authentication.getPrincipal();
        var person = userDetails.getPerson();
        var tokenJWT = tokenService.generateToken(person);

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
