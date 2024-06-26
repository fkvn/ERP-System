package com.tedkvn.erp.repository;

import com.tedkvn.erp.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //    List<User> findByIsDeletedFalse();

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String query);

    Optional<User> findByPhone(String query);

    Optional<User> findByPhoneAndPhoneRegion(String phone, String region);

    Optional<User> findBySub(String sub);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("select u from User u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    List<User> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
