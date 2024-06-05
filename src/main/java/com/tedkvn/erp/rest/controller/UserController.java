package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> findAllUsers(@Valid @RequestBody SignUpRequest req) {
        return userService.findAllUsers();
    }

    
}
