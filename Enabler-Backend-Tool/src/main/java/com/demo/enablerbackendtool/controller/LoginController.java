package com.demo.enablerbackendtool.controller;

import com.demo.enablerbackendtool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.demo.enablerbackendtool.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        logger.info("Login attempt for user: " + loginRequest.getUsername());

        // Debug logging for received username and password
        logger.debug("Received username: " + loginRequest.getUsername());
        logger.debug("Received password: " + loginRequest.getPassword());

        boolean loginSuccessful = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (loginSuccessful) {
            logger.info("Login successful for user: " + loginRequest.getUsername());
            return ResponseEntity.ok("Login successful");
        } else {
            logger.warn("Login failed for user: " + loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

