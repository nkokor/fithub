package com.fithub.services.chat.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fithub.services.chat.dao.model.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}