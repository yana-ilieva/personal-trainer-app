package com.fitbook.repository;

import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.chat.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.chat = ?1")
    List<Message> getMessages(Chat chat, Pageable pageable);
}
