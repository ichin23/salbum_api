package com.ichin23.salbum.controllers;

import com.ichin23.salbum.controllers.docs.AuthControllerDocs;
import com.ichin23.salbum.controllers.exceptions.ExceptionDTO;
import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.domain.user.dto.TokenResponseDTO;
import com.ichin23.salbum.domain.user.dto.UserDTO;
import com.ichin23.salbum.domain.user.dto.UserLoginDTO;
import com.ichin23.salbum.domain.user.dto.UserRegisterDTO;
import com.ichin23.salbum.services.TokenService;
import com.ichin23.salbum.services.UserService;
import com.ichin23.salbum.services.exceptions.UserExistsException;
import com.ichin23.salbum.services.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthController implements AuthControllerDocs {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;


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

            var response = new TokenResponseDTO(token, "bearer", new UserDTO(user));
            return ResponseEntity.ok(response);
        }catch(UserNotFound exception){
            return ResponseEntity.status(403).body(new ExceptionDTO("Email ou senha não encontrados", exception.getMessage()));
        }
    }
}
