package com.pawbla.project.home.user.management.controllers;

import com.pawbla.project.home.user.management.services.ManageUsersService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRenderer {
    @Autowired
    private ManageUsersService manageUsers;

    public Optional<String> getUserRendered(String username) {
        return Optional.ofNullable(manageUsers.getUserByName(username).map(user ->
                new JSONObject()
                        .put("user_id", user.getId())
                        .put("username", user.getUserName())
                        .put("firstName", user.getFirstName())
                        .put("lastName", user.getLastName())
                        .put("email", user.getEmail())
                        .put("enabled", user.isEnabled())
                        .put("role", user.getRole().getRole())
                        .toString()
                ).orElse(null));

    }

    public String getUsers() {
        JSONArray array = new JSONArray();
        manageUsers.getUsers().forEach(user -> {
            JSONObject userItem = new JSONObject()
                    .put("user_id", user.getId())
                    .put("username", user.getUserName())
                    .put("firstName", user.getFirstName())
                    .put("lastName", user.getLastName())
                    .put("enabled", user.isEnabled())
                    .put("role", user.getRole().getRole())
                    .put("email", user.getEmail());
            array.put(userItem);
        });
        return new JSONObject().put("users", array).toString();
    }

    public String getRoles() {
        JSONArray array = new JSONArray();
        manageUsers.getRoles().forEach(user -> {
            JSONObject userItem = new JSONObject()
                    .put("role_id", user.getId())
                    .put("role", user.getRole());
            array.put(userItem);
        });
        return new JSONObject().put("roles", array).toString();
    }
}
