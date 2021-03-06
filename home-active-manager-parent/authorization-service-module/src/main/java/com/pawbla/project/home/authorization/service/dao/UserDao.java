package com.pawbla.project.home.authorization.service.dao;

import com.pawbla.project.home.authorization.service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername (final String username);
}
