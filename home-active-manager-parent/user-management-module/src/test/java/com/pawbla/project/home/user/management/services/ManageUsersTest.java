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

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("userName1", user.getUserName());
        assertEquals("password1", user.getPassword());
        assertFalse(user.isEnabled());
    }

    @Test
    public void getUsersList() {
        List<User> userList = manageUsers.getUsers();
        assertEquals(2, userList.size());
        assertEquals("userName1", userList.get(0).getUserName());
        assertEquals("password1", userList.get(0).getPassword());
        assertFalse(userList.get(0).isEnabled());
        assertEquals("userName2", userList.get(1).getUserName());
        assertEquals("password2", userList.get(1).getPassword());
        assertTrue(userList.get(1).isEnabled());
    }

    @Test
    public void getRolesList() {
        List<Role> roleList = manageUsers.getRoles();
        assertEquals(2, roleList.size());
        assertEquals("ROLE_USER", roleList.get(0).getRole());
        assertEquals("ROLE_ADMIN", roleList.get(1).getRole());
    }

    @Test
    public void addUser() {
        User userToAdd = new User();
        userToAdd.setUserName("userToAdd");
        userToAdd.setFirstName("firstName");
        userToAdd.setPassword("password");
        manageUsers.addUser(userToAdd);
        verify(manageUsersDao).save(userCaptor.capture());
        assertEquals("userToAdd", userCaptor.getValue().getUserName());
        assertEquals("firstName", userCaptor.getValue().getFirstName());
        assertEquals("encodedPassword", userCaptor.getValue().getPassword());
        assertFalse(userCaptor.getValue().isEnabled());
    }

    @Test
    public void removeUser() {
        int code = manageUsers.removeUser(1);
        verify(manageUsersDao).delete(userCaptor.capture());
        assertEquals(0, code);
        assertEquals("userName1", userCaptor.getValue().getUserName());
        assertEquals("password1", userCaptor.getValue().getPassword());
    }

    @Test
    public void noRemoveAdmin() {
        int code = manageUsers.removeUser(2);
        assertEquals(-1, code);
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
        assertEquals(0, code);
        verify(manageUsersDao).save(userCaptor.capture());
        assertEquals("updatedFirstName", userCaptor.getValue().getFirstName());
        assertEquals("updatedLastName", userCaptor.getValue().getLastName());
        assertEquals("updatedEmail", userCaptor.getValue().getEmail());
        assertEquals("ROLE_GUEST", userCaptor.getValue().getRole().getRole());
        assertTrue(userCaptor.getValue().isEnabled());
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
        assertEquals(-1, code);
        verify(manageUsersDao, never()).save(any());
    }

    @Test
    public void updatePassword() {
        Mockito.lenient().when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        int code = manageUsers.updatePassword(2,"pass1", "pass2");
        assertEquals(0, code);
        verify(manageUsersDao, atLeastOnce()).updatePassword(2, "encodedPassword");
    }

    @Test
    public void noUpdateEqualOldAndNewPassword() {
        int code = manageUsers.updatePassword(2,"pass1", "pass1");
        assertEquals(-1, code);
        verify(manageUsersDao, never()).updatePassword(anyInt(), anyString());
    }

    @Test
    public void noUpdateIncorrectOldPassword() {
        Mockito.lenient().when(passwordEncoder.matches(anyString(),anyString())).thenReturn(false);
        int code = manageUsers.updatePassword(2,"pass1", "pass2");
        assertEquals(-2, code);
        verify(manageUsersDao, never()).updatePassword(anyInt(), anyString());
    }
}