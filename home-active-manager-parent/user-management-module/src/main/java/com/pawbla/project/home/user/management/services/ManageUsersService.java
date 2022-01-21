package com.pawbla.project.home.user.management.services;

import com.pawbla.project.home.user.management.models.Role;
import com.pawbla.project.home.user.management.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ManageUsersService {
    Optional <User> getUserByName(final String nickname);
    List<User> getUsers();
    List<Role> getRoles();
    void addUser(final User user);
    int removeUser(final int user_id);
    int updateUser(final int user_id, final User user);
    int updatePassword(final int user_id, String oldPassword, String newPassword);
}

