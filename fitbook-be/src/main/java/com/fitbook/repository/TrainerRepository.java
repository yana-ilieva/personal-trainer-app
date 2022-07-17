package com.fitbook.repository;

import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>, JpaSpecificationExecutor<Trainer> {

    Trainer findByUser(User user);

    @Query("select t from Trainer t join t.user u where u.deleted = false")
    List<Trainer> findAllByDeletedFalse(Pageable pageable);
}
