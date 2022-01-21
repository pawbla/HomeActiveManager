package com.pawbla.project.home.user.management.controllers;

import com.pawbla.project.home.user.management.models.Password;
import com.pawbla.project.home.user.management.models.User;
import com.pawbla.project.home.user.management.services.ManageUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/usermanagement")
public class RestControllers {

    private static final String STORED_SUCCESSFULLY_MSG = "user stored succesfully";

    @Autowired
    private UserRenderer userRenderer;

    @Autowired
    private ManageUsersService manageUsers;

    @GetMapping(value = "/user", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> loggedUserDatas(@RequestParam("login") String login) {
        return userRenderer.getUserRendered(login).map(
                ResponseEntity::ok
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @GetMapping(value = "/users", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body(userRenderer.getUsers());
    }

    @GetMapping(value = "/roles", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getRoles() {
        return ResponseEntity.ok().body(userRenderer.getRoles());
    }

    @PostMapping(value = "/user", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        manageUsers.addUser(user);
        return ResponseEntity.ok().body(STORED_SUCCESSFULLY_MSG);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        int code = manageUsers.removeUser(userId);
        if (code == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User user) {
        int code = manageUsers.updateUser(userId, user);
        if (code == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("password/{userId}")
    public  ResponseEntity<String> updatePassword (@PathVariable int userId, @RequestBody Password passwordUpdate) {
        int code = manageUsers.updatePassword(userId, passwordUpdate.getOldPassword(), passwordUpdate.getNewPassword());
        if (code == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

