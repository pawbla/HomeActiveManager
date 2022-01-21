package com.pawbla.project.home.user.management.services;

import com.pawbla.project.home.user.management.dao.ManageUsersDao;
import com.pawbla.project.home.user.management.models.Role;
import com.pawbla.project.home.user.management.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManageUsers implements ManageUsersService {

    private final static String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private ManageUsersDao manageUsersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByName(String userName) {
        return Optional.ofNullable(manageUsersDao.findByUsername(userName));
    }

    @Override
    public List<User> getUsers() {
        return manageUsersDao.findUsers();
    }

    @Override
    public List<Role> getRoles() {
        return manageUsersDao.findRoles();
    }

    @Override
    public void addUser(User user) {
        //always store user with role as below
        user.setRole(manageUsersDao.findRole(DEFAULT_ROLE));
        //password should be encoded before save
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user should be allways stored as disabled
        if (user.isEnabled()) {
            user.setEnabled(false);
        }
        manageUsersDao.save(user);
    }

    @Override
    public int removeUser(int user_id) {
        User user = manageUsersDao.findByUserId(user_id);
        //do not allow to remove all admins from database
        if (!allowRemoveIfAdmin(user.getRole())) {
            return -1;
        }
        manageUsersDao.delete(user);
        return 0;
    }

    @Override
    public int updateUser(int user_id, User user) {
        User userDao = manageUsersDao.findByUserId(user_id);
        if (!allowChangeIfAdminRole(userDao.getRole(), user.getRole().getRole(), user.isEnabled())) {
            return -1;
        }
        userDao.setEmail(user.getEmail());
        userDao.setEnabled(user.isEnabled());
        userDao.setFirstName(user.getFirstName());
        userDao.setLastName(user.getLastName());
        userDao.setRole(manageUsersDao.findRole(user.getRole().getRole()));
        manageUsersDao.save(userDao);
        return 0;
    }

    @Override
    public int updatePassword(int user_id, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return -1;
        } else if (isOldPasswordCorrect(oldPassword, user_id)) {
            manageUsersDao.updatePassword(user_id, passwordEncoder.encode(newPassword));
            return 0;
        } else {
            return -2;
        }
    }

    private boolean allowRemoveIfAdmin(Role role) {
        boolean allowOperation = true;
        if ("ROLE_ADMIN".equals(role.getRole()) && manageUsersDao.countAdminRecords() <= 1) {
            allowOperation = false;
        }
        return allowOperation;
    }

    private boolean allowChangeIfAdminRole(final Role role, String updatedRole, boolean updatedEnable) {
        boolean allowOperation = true;
        if ("ROLE_ADMIN".equals(role.getRole()) && manageUsersDao.countAdminRecords() <= 1) {
            if (!("ROLE_ADMIN".equals(updatedRole) && updatedEnable)) {
                allowOperation = false;
            }
        }
        return allowOperation;
    }

    private boolean isOldPasswordCorrect(String oldPassword, int user_id) {
        return passwordEncoder.matches(oldPassword, manageUsersDao.findByUserId(user_id).getPassword());
    }
}
