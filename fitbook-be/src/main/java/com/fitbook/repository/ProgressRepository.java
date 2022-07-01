package com.fitbook.repository;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.client.Progress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByClient(Client client, Pageable pageable);
}
