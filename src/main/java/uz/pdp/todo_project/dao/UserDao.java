package uz.pdp.todo_project.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.todo_project.domain.User;


@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(User user){
         jdbcTemplate.update("insert into users(username, email, password) VALUES (?, ?, ?)",
                user.getUsername(),
                user.getEmail(),
                user.getPassword());

    }

    public User getUserByUsername(String username){
        User user = jdbcTemplate.queryForObject("select * from users where username = ?",
                new BeanPropertyRowMapper<>(User.class),
                username);
        System.out.println(user);
        return user;

    }

    public User getByEmail(String email){
        return jdbcTemplate.queryForObject("select * from users where email = ?", new BeanPropertyRowMapper<>(User.class), email);
    }

}
