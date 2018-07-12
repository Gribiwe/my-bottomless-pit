import database.Person;
import hibernate.Factory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {


        Person person1 = new Person();
        person1.setLogin("nikita.grib@gmail.com");
        person1.setNick("Gribiwe");
        person1.setPassword("******");
        person1.setAvatar("img2.jpg");
        person1.setCreated(Timestamp.from(Instant.now()));
//        person1.setExpired(null);

        try {
            Factory.getInstance().getPersonDAO().addPerson(person1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
