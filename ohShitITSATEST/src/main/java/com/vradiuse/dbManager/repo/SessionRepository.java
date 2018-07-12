package com.vradiuse.dbManager.repo;

import com.vradiuse.dbManager.database.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Integer> {
}
