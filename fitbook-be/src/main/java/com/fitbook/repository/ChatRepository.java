package com.fitbook.repository;

import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByClientAndTrainer(Client client, Trainer trainer);
}
