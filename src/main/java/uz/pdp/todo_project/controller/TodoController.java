package uz.pdp.todo_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.todo_project.dao.TodoDao;
import uz.pdp.todo_project.domain.Todo;
import uz.pdp.todo_project.dto.TodoDto;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoDao todoDao;

    @GetMapping
    public String todo(){
        return "show-todo";
    }

    @GetMapping("/addtodo")
    public ModelAndView addTodo(ModelAndView modelAndView){
        modelAndView.setViewName("todo");
        modelAndView.addObject("get", todoDao.getAll());

        return modelAndView;

    }

    @PostMapping("/addtodo")
    public ModelAndView createTodo(
            @ModelAttribute TodoDto todoDto,
            ModelAndView modelAndView){

        Todo todo = Todo.builder()
                .title(todoDto.title())
                .description(todoDto.description())
                .build();
        todoDao.save(todo);

        modelAndView.setViewName("show-todo");

        return modelAndView;
    }
}
