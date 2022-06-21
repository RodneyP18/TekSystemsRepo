package org.rodneyparshall.rightrx.controller;

import org.rodneyparshall.rightrx.dto.ResponseDTO;
import org.rodneyparshall.rightrx.dto.UserDTO;
import org.rodneyparshall.rightrx.session.InMemSessionRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    public AuthenticationManager manager;
    public InMemSessionRegistry inMemSessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        final String sessionId = inMemSessionRegistry.registerSession(user.getEmail());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }
}
