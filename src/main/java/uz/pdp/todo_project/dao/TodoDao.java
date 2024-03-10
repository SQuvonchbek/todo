package uz.pdp.todo_project.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.todo_project.domain.Todo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(Todo todo){
        jdbcTemplate.update("insert into todo(title, description, due_date) values (?, ?, ?)",
                todo.getTitle(),
                todo.getDescription(),
                todo.getDueDate()
                );
    }
    public List<Todo> getAll() {
        return jdbcTemplate.query("select * from todo", new BeanPropertyRowMapper<>(Todo.class));

    }
}
