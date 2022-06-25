package com.fitbook.repository;

import com.fitbook.entity.File;
import com.fitbook.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByUser(User user);
}
