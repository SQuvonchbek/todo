package uz.pdp.todo_project.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private String description;
    private String dueDate;

}
