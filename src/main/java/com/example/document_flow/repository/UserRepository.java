package com.example.document_flow.repository;

import com.example.document_flow.entity.enums.Permission;
import com.example.document_flow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByPassword(String password);
    Boolean existsByLoginAndPassword(String login, String password);
    Boolean existsByLogin(String login);
    Optional<User> deleteByLogin(String login);
    Boolean existsByPermission(Permission permission);
}
