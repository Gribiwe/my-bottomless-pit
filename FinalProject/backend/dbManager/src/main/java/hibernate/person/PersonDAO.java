package hibernate.person;

import database.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

    void addPerson(Person person) throws SQLException;

    void updatePerson(Person person) throws SQLException;

    Person getPersonById(int id) throws SQLException;

    List getAllPersons() throws SQLException;

    void deletePerson(Person person) throws SQLException;

}