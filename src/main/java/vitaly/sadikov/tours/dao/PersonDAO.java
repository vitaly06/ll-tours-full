package vitaly.sadikov.tours.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vitaly.sadikov.tours.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public PersonDAO(){
        this.jdbcTemplate = null;
    }

    // Добавление заявки
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO requests VALUES(?, ?)", person.getName(), person.getNumber());
    }
}
