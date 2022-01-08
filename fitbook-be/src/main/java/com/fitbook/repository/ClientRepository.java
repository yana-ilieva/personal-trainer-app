package com.fitbook.repository;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.client.Progress;
import com.fitbook.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select p from Client c join c.progresses p where c.id = ?1")
    List<Progress> getProgress(Long id, Pageable pageable);

    @Query("select c.id from Client c where c.user.email like ?1")
    Long getIdByEmail(String email);

    Client findByUser(User user);
}
