package com.ai.friday.contoller;

import com.ai.friday.DTOS.loginSignup.LoginReq;
import com.ai.friday.DTOS.loginSignup.LoginRes;
import com.ai.friday.DTOS.loginSignup.SignUpReq;
import com.ai.friday.entities.UserEntity;
import com.ai.friday.exceptions.LoginFailedException;
import com.ai.friday.jwtComponents.JwtUtils;
import com.ai.friday.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Validated
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils utils;

    @Autowired
    UserRepo repo;

    @PostMapping("/auth/login")
    public LoginRes login(@RequestBody @Valid LoginReq req) throws LoginFailedException {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                req.getEmail(),
                                req.getPassword()
                        )
                );

        UserEntity user = repo.findByEmail(req.getEmail())
                .orElseThrow(LoginFailedException::new);

        String token = utils.generateToken(user);

        return new LoginRes(
                token,
                user.getPermissions().contains("admin")
        );
    }

    @GetMapping("/auth/username_match")
    public boolean checkUsername(@RequestBody String username){
        return repo.existsByEmail(username);
    }


    @PostMapping("/auth/signup")
    public LoginRes signUp(@RequestBody @Valid SignUpReq req){
        UserEntity entity = new UserEntity(
                null,
                req.getEmail(),
                encoder.encode(req.getPassword()),
                req.getName(),
                Set.of("user")
        );
        return  new LoginRes(
                utils.generateToken(repo.save(entity)),
                false
        );
    }
}
