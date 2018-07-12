package com.vradiuse.dbManager.repo;

import com.vradiuse.dbManager.database.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
