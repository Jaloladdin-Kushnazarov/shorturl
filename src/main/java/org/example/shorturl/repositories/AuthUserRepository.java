package org.example.shorturl.repositories;

import org.example.shorturl.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {


    @Query("SELECT a FROM AuthUser a WHERE UPPER(a.username) = UPPER(?1)")
    Optional<AuthUser> findByUsername(String username);

    @Query("select a from AuthUser a where upper(a.email) = upper(?1) and a.deleted = false")
    Optional<AuthUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.active = true where a.id = ?1 and a.deleted = false")
    void activateUser( Long id);

}
