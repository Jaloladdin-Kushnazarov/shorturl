package org.example.shorturl.repositories;

import org.example.shorturl.entities.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("select u from Url u where upper(u.code) = upper(?1) and  u.deleted = false ")
    Optional<Url> findByCode(String code);

    @Query("select u from Url u where u.createdBy = ?1 and u.deleted = false  ")
    Page<Url> findAllByUserID(Long createdBy, Pageable pageable);

    @Query("select u from Url u where u.createdBy = ?1 and u.createdAt between ?2 and ?3 and u.deleted = false")
    List<Url> findAllByUSer(Long userId, LocalDateTime from, LocalDateTime to);



}
