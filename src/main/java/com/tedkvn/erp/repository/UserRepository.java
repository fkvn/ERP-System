package com.tedkvn.erp.repository;

import com.tedkvn.erp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIsDeletedFalse();
}
