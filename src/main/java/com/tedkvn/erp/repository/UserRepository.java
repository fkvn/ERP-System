package com.tedkvn.erp.repository;

import com.tedkvn.erp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIsDeletedFalse();

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String query);

    Optional<User> findByPhone(String query);
}
