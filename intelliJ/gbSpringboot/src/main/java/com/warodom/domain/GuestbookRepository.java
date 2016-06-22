package com.warodom.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wwaro on 7/6/2559.
 */
public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
    Guestbook findById(long id);

    List<Guestbook> findByName(@Param("name") String name);
    List<Guestbook> findAll();

    Page<Guestbook> findAll(Pageable pageable);
}