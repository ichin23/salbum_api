package com.ichin23.salbum.controllers;

import com.ichin23.salbum.controllers.docs.UserControllerDocs;
import com.ichin23.salbum.domain.user.dto.UserDTO;
import com.ichin23.salbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController implements UserControllerDocs {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID id){
        var user = userService.findUserById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(new UserDTO(user.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
