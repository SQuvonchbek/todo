package uz.pdp.todo_project.dto;

import java.time.LocalDateTime;

public record TodoDto(
     String title,
     String description,
     LocalDateTime due_date
) {

}
