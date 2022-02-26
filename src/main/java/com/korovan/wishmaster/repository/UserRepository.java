package com.korovan.wishmaster.repository;

import com.korovan.wishmaster.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String Name);
}