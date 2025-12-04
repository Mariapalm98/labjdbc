package se.iths.maria.anslutning.singel;

import java.util.List;

public interface PersonDAO {

    List<Person> findAll();

    void insert(Person person);

}
