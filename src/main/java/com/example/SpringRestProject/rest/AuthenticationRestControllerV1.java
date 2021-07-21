package com.example.SpringRestProject.rest;

import com.example.SpringRestProject.model.UserAuth;
import com.example.SpringRestProject.repository.UserAuthRepository;
import com.example.SpringRestProject.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userAuthRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserAuthRepository userAuthRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userAuthRepository = userAuthRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestDTO requestDTO){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(),requestDTO.getPassword()));
            UserAuth userAuth = userAuthRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() ->
                    new UsernameNotFoundException("UserAuth does not exists"));
            String token = jwtTokenProvider.createToken(requestDTO.getEmail(),userAuth.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email" , requestDTO.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request , HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request , response,null);
    }
}
