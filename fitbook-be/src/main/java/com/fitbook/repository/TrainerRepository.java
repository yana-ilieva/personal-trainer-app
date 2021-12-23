package com.fitbook.repository;

import com.fitbook.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>, JpaSpecificationExecutor<Trainer> {

    @Query("select t.id from Trainer t where t.user.email like ?1")
    Long getIdByEmail(String email);
}
