package com.pawbla.project.home.user.management.dao;

import com.pawbla.project.home.user.management.models.Role;
import com.pawbla.project.home.user.management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ManageUsersDao extends JpaRepository<User, Long> {
    public User findByUsername (final String username);

    @Query("FROM User")
    public List<User> findUsers();

    @Query("FROM Role")
    public List<Role> findRoles();

    @Query("FROM User WHERE userId=?1")
    public User findByUserId(final int user_id);

    @Query("FROM Role WHERE role=?1")
    public Role findRole(final String role);

    @Query("SELECT count(*) FROM User u WHERE u.role.roleId = (SELECT roleId FROM Role WHERE role='ROLE_ADMIN')")
    public int countAdminRecords();

    @Modifying
    @Transactional
    @Query("UPDATE User SET password = ?2 WHERE userId = ?1")
    public void updatePassword(final int user_id, String password);
}
