package com.pawbla.project.home.user.management.services;

import com.pawbla.project.home.user.management.dao.ManageUsersDao;
import com.pawbla.project.home.user.management.models.Role;
import com.pawbla.project.home.user.management.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageUsersTest {

    @Mock
    private ManageUsersDao manageUsersDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ManageUsers manageUsers;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setMockOutput() {
        Role role1 = new Role();
        role1.setRole("ROLE_USER");

        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");

        Role role3 = new Role();
        role3.setRole("ROLE_GUEST");

        User user1 = new User();
        user1.setUserName("userName1");
        user1.setPassword("password1");
        user1.setEnabled(false);
        user1.setRole(role1);

        User user2 = new User();
        user2.setUserName("userName2");
        user2.setPassword("password2");
        user2.setEnabled(true);
        user2.setRole(role2);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        List<Role> roleList = new ArrayList<>();
        roleList.add(role1);
        roleList.add(role2);

        Mockito.lenient().when(manageUsersDao.findByUsername(anyString())).thenReturn(user1);
        Mockito.lenient().when(manageUsersDao.findUsers()).thenReturn(userList);
        Mockito.lenient().when(manageUsersDao.findRoles()).thenReturn(roleList);
        Mockito.lenient().when(manageUsersDao.findRole(any())).thenReturn(role3);
        Mockito.lenient().when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        Mockito.lenient().when(manageUsersDao.findByUserId(1)).thenReturn(user1);
        Mockito.lenient().when(manageUsersDao.findByUserId(2)).thenReturn(user2);
        Mockito.lenient().when(manageUsersDao.countAdminRecords()).thenReturn(1);
    }

    @Test
    public void getUserDetails() {
        User user = manageUsers.getUserByName("user").get();
        assertEquals("First name for user", "userName1", user.getUserName());
        assertEquals("Password for user","password1", user.getPassword());
        assertFalse("Enabled for user", user.isEnabled());
    }

    @Test
    public void getUsersList() {
        List<User> userList = manageUsers.getUsers();
        assertEquals("Users list size", 2, userList.size());
        assertEquals("First name for first user", "userName1", userList.get(0).getUserName());
        assertEquals("Password for first user","password1", userList.get(0).getPassword());
        assertFalse("Enabled for first user", userList.get(0).isEnabled());
        assertEquals("First name second user", "userName2", userList.get(1).getUserName());
        assertEquals("Password for second user","password2", userList.get(1).getPassword());
        assertTrue("Enabled for second user", userList.get(1).isEnabled());
    }

    @Test
    public void getRolesList() {
        List<Role> roleList = manageUsers.getRoles();
        assertEquals("Roles list size", 2, roleList.size());
        assertEquals("First role", "ROLE_USER", roleList.get(0).getRole());
        assertEquals("Second role", "ROLE_ADMIN", roleList.get(1).getRole());
    }

    @Test
    public void addUser() {
        User userToAdd = new User();
        userToAdd.setUserName("userToAdd");
        userToAdd.setFirstName("firstName");
        userToAdd.setPassword("password");
        manageUsers.addUser(userToAdd);
        verify(manageUsersDao).save(userCaptor.capture());
        assertEquals("Stored username", "userToAdd", userCaptor.getValue().getUserName());
        assertEquals("Stored first name", "firstName", userCaptor.getValue().getFirstName());
        assertEquals("Stored password", "encodedPassword", userCaptor.getValue().getPassword());
        assertFalse("Stored user id disabled", userCaptor.getValue().isEnabled());
    }

    @Test
    public void removeUser() {
        int code = manageUsers.removeUser(1);
        verify(manageUsersDao).delete(userCaptor.capture());
        assertEquals("Correctly removed user code", 0, code);
        assertEquals("Removed username", "userName1", userCaptor.getValue().getUserName());
        assertEquals("Removed password", "password1", userCaptor.getValue().getPassword());
    }

    @Test
    public void noRemoveAdmin() {
        int code = manageUsers.removeUser(2);
        assertEquals("User cannot be removed", -1, code);
        verify(manageUsersDao, never()).delete(any());
    }

    @Test
    public void updateUser() {
        User userUpdate = new User();
        userUpdate.setFirstName("updatedFirstName");
        userUpdate.setLastName("updatedLastName");
        userUpdate.setEnabled(true);
        userUpdate.setEmail("updatedEmail");
        Role roleUpdate = new Role();
        roleUpdate.setRole("ROLE_GUEST");
        userUpdate.setRole(roleUpdate);
        int code = manageUsers.updateUser(1,userUpdate);
        assertEquals("Correctly removed user code", 0, code);
        verify(manageUsersDao).save(userCaptor.capture());
        assertEquals("Updated first name", "updatedFirstName", userCaptor.getValue().getFirstName());
        assertEquals("Updated last name", "updatedLastName", userCaptor.getValue().getLastName());
        assertEquals("Updated email", "updatedEmail", userCaptor.getValue().getEmail());
        assertEquals("Updated role", "ROLE_GUEST", userCaptor.getValue().getRole().getRole());
        assertTrue("Updated user id enabled", userCaptor.getValue().isEnabled());
    }

    @Test
    public void noChangeAdminToUserWhenLastAdmin() {
        User userUpdate = new User();
        userUpdate.setFirstName("updatedFirstName");
        userUpdate.setLastName("updatedLastName");
        userUpdate.setEnabled(false);
        userUpdate.setEmail("updatedEmail");
        Role roleUpdate = new Role();
        roleUpdate.setRole("ROLE_GUEST");
        userUpdate.setRole(roleUpdate);
        int code = manageUsers.updateUser(2,userUpdate);
        assertEquals("User cannot be updated", -1, code);
        verify(manageUsersDao, never()).save(any());
    }

    @Test
    public void updatePassword() {
        Mockito.lenient().when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        int code = manageUsers.updatePassword(2,"pass1", "pass2");
        assertEquals("User cannot be updated", 0, code);
        verify(manageUsersDao, atLeastOnce()).updatePassword(2, "encodedPassword");
    }

    @Test
    public void noUpdateEqualOldAndNewPassword() {
        int code = manageUsers.updatePassword(2,"pass1", "pass1");
        assertEquals("User cannot be updated", -1, code);
        verify(manageUsersDao, never()).updatePassword(anyInt(), anyString());
    }

    @Test
    public void noUpdateIncorrectOldPassword() {
        Mockito.lenient().when(passwordEncoder.matches(anyString(),anyString())).thenReturn(false);
        int code = manageUsers.updatePassword(2,"pass1", "pass2");
        assertEquals("User cannot be updated", -2, code);
        verify(manageUsersDao, never()).updatePassword(anyInt(), anyString());
    }
}