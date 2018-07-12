package com.vradiuse.dbManager.repo;

import com.vradiuse.dbManager.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findPersonByLogin(String login);

}
