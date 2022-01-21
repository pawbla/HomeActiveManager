package com.pawbla.project.home.user.management.dao;

import com.pawbla.project.home.user.management.configurations.DbConfiguration;
import com.pawbla.project.home.user.management.configurations.DbSourceConfigurationDev;
import com.pawbla.project.home.user.management.models.Role;
import com.pawbla.project.home.user.management.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfiguration.class, DbSourceConfigurationDev.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ManageUsersDaoTest {

    @Autowired
    private ManageUsersDao manageUsersDao;

    @Test
    public void getUserDetails() {
        User user = manageUsersDao.findByUsername("user");
        assertEquals("First name for user", "userName1", user.getFirstName());
        assertEquals("Password for user",
                "$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
        assertFalse("Enabled for user", user.isEnabled());
        assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());
    }

    @Test
    public void getUsers() {
        //given
        List<String> usernames = new ArrayList<String>();
        usernames.add("user");
        usernames.add("guest");
        usernames.add("admin");
        usernames.add("deleteUser");
        usernames.add("updateUser");
        usernames.add("UserPassChange");
        usernames.add("AddUserNick");

        List<String> roles = new ArrayList<String>();
        roles.add("ROLE_USER");
        roles.add("ROLE_GUEST");
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_USER");
        roles.add("ROLE_USER");
        roles.add("TEST_ROLE");
        //when
        List<User> users = manageUsersDao.findUsers();
        //then
        assertEquals("List should contain 6 users", 6, users.size());
        users.forEach(user -> {
            assertEquals("UserName for user: " + user.getId(), usernames.get(user.getId()-1), user.getUserName());
            assertEquals("Role for user: " + user.getId(), roles.get(user.getId()-1), user.getRole().getRole());
        });
    }

    @Test
    public void getRoles() {
        List<Role> roles = manageUsersDao.findRoles();
        assertEquals("List should contain 3 users", 3, roles.size());
        assertEquals("First role ID", 1, roles.get(0).getId());
        assertEquals("First role name", "ROLE_USER", roles.get(0).getRole());
        assertEquals("Second role ID", 2, roles.get(1).getId());
        assertEquals("Second role name", "ROLE_ADMIN", roles.get(1).getRole());
        assertEquals("Third role ID", 3, roles.get(2).getId());
        assertEquals("Third role name", "ROLE_GUEST", roles.get(2).getRole());
    }

    @Test
    public void getUserById() {
        //given
        int user_id = 1;
        //when
        //then
        User user = manageUsersDao.findByUserId(user_id);
        Assert.assertEquals("First name for user", "userName1", user.getFirstName());
        Assert.assertEquals("Password for user",
                "$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
        Assert.assertFalse("Enabled for user", user.isEnabled());
        Assert.assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());
    }

    @Test
    public void getRole() {
        Assert.assertEquals("Role admin should be fetched", "ROLE_ADMIN", manageUsersDao.findRole("ROLE_ADMIN").getRole());
        Assert.assertEquals("Role user should be fetched", "ROLE_USER", manageUsersDao.findRole("ROLE_USER").getRole());
        Assert.assertEquals("Role guest should be fetched", "ROLE_GUEST", manageUsersDao.findRole("ROLE_GUEST").getRole());
    }

    @Test
    public void getIncorrectRole() {
        Assert.assertNull("Empty object expected", manageUsersDao.findRole("No_existing_role"));
    }


    @Test
    public void countAdminRecords () {
        //given
        int expectedNoAdmins = 1;
        //when
        //then
        Assert.assertEquals("One admin available in databse", expectedNoAdmins, manageUsersDao.countAdminRecords());
    }

    @Test
    public void updatePassword() {
        //given
        String NEW_PASSWORD = "nowe_haslo";
        int USER_ID = 6;
        //when
        manageUsersDao.updatePassword(USER_ID, NEW_PASSWORD);
        //then
        User user = manageUsersDao.findByUserId(USER_ID);
        Assert.assertEquals("New password", NEW_PASSWORD, user.getPassword());
    }

    @Test
    public void addUser() {
        //given
        String NICKNAME = "AddUserNick";
        String PASSWORD = "AddUserPass";
        String FIRST_NAME = "FirstName";
        String LAST_NAME = "LastName";
        String EMAIL = "user@add.user";
        String ROLE = "TEST_ROLE";

        User testUser = new User();
        testUser.setUserName(NICKNAME);
        testUser.setPassword(PASSWORD);
        testUser.setFirstName(FIRST_NAME);
        testUser.setLastName(LAST_NAME);
        testUser.setEmail(EMAIL);
        testUser.setEnabled(false);

        Role role = new Role();
        role.setRole(ROLE);
        testUser.setRole(role);
        //when
        manageUsersDao.save(testUser);
        //then
        User user = manageUsersDao.findByUsername(NICKNAME);
        Assert.assertEquals("Nickname for added user", NICKNAME, user.getUserName());
        Assert.assertEquals("First name for added user", FIRST_NAME, user.getFirstName());
        Assert.assertEquals("Last name for added user", LAST_NAME, user.getLastName());
        Assert.assertFalse("Enabled for added user", user.isEnabled());
        Assert.assertEquals("Email for added user", EMAIL, user.getEmail());
        Assert.assertEquals("Password for guest", PASSWORD, user.getPassword());
        Assert.assertEquals("Role for added user", ROLE, user.getRole().getRole());
    }

    @Test //PUT
    public void updateUser() {
        //given
        String NICKNAME = "updateUser";
        String FIRST_NAME = "NewFirstName";
        String LAST_NAME = "NewLastName";
        String EMAIL = "new@email.user";
        String ROLE = "ROLE_USER";

        User testUser = manageUsersDao.findByUsername(NICKNAME);
        testUser.setUserName(NICKNAME);
        testUser.setFirstName(FIRST_NAME);
        testUser.setLastName(LAST_NAME);
        testUser.setEmail(EMAIL);
        testUser.setEnabled(false);

        Role role = manageUsersDao.findRole(ROLE);
        testUser.setRole(role);

        //when
        manageUsersDao.save(testUser);

        //then
        User user = manageUsersDao.findByUsername(NICKNAME);
        Assert.assertEquals("Nickname for updated user", NICKNAME, user.getUserName());
        Assert.assertEquals("First name for updated user", FIRST_NAME, user.getFirstName());
        Assert.assertEquals("Last name for updated user", LAST_NAME, user.getLastName());
        Assert.assertFalse("Enabled for updated user", user.isEnabled());
        Assert.assertEquals("Email for updated user", EMAIL, user.getEmail());
        Assert.assertEquals("Role for updated user", ROLE, user.getRole().getRole());
    }

    @Test
    public void deleteUser() {
        //given
        String NICKNAME = "deleteUser";
        User user = manageUsersDao.findByUsername(NICKNAME);
        Assert.assertNotNull("User exists in database", user);
        //when
        manageUsersDao.delete(user);
        //then
        Assert.assertNull("User not exists in database", manageUsersDao.findByUsername(NICKNAME));
    }
}