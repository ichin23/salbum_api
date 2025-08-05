package com.ichin23.salbum.controllers;

import com.ichin23.salbum.controllers.docs.AuthControllerDocs;
import com.ichin23.salbum.controllers.exceptions.ExceptionDTO;
import com.ichin23.salbum.domain.refreshToken.dto.RefreshTokenInput;
import com.ichin23.salbum.domain.refreshToken.dto.RefreshTokenOutput;
import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.domain.user.dto.TokenResponseDTO;
import com.ichin23.salbum.domain.user.dto.UserDTO;
import com.ichin23.salbum.domain.user.dto.UserLoginDTO;
import com.ichin23.salbum.domain.user.dto.UserRegisterDTO;
import com.ichin23.salbum.services.RefreshTokenService;
import com.ichin23.salbum.services.TokenService;
import com.ichin23.salbum.services.UserService;
import com.ichin23.salbum.services.exceptions.TokenRefreshException;
import com.ichin23.salbum.services.exceptions.UserExistsException;
import com.ichin23.salbum.services.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RestController
public class AuthController implements AuthControllerDocs {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenInput data){
        try{
            var result = refreshTokenService.validateAndGenerateNewAccessToken(data.refreshToken());
            return ResponseEntity.ok(new RefreshTokenOutput(result));
        }catch (TokenRefreshException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO data){
        try{
            userService.validateUser(data.email(), data.username());

            var passwordCrypto = new BCryptPasswordEncoder().encode(data.password());

            var user = new User(data.name(), data.username(), data.email(), passwordCrypto);

            var userFinal = userService.registerUser(user);

            return ResponseEntity.ok(new UserDTO(userFinal));
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body(new ExceptionDTO("Erro ao criar o usuário", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO data){
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var user = (User) authenticationManager.authenticate(usernamePassword).getPrincipal();
            var token = tokenService.generateToken(user);

            var refresh_token = refreshTokenService.createRefreshToken(user);

            var response = new TokenResponseDTO(token, "bearer", refresh_token, new UserDTO(user));
            return ResponseEntity.ok(response);
        }catch(UserNotFound exception){
            return ResponseEntity.status(403).body(new ExceptionDTO("Email ou senha não encontrados", exception.getMessage()));
        }
    }
}
