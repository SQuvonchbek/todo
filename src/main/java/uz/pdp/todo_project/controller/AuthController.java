package uz.pdp.todo_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.todo_project.dao.UserDao;
import uz.pdp.todo_project.domain.User;
import uz.pdp.todo_project.dto.UserLoginDto;
import uz.pdp.todo_project.dto.UserSignUpDto;
import uz.pdp.todo_project.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDao userDao;
    private final UserService userService;

    @GetMapping
    public String signUp(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }


    @PostMapping("/signup")
    public ModelAndView SignUp(
            @ModelAttribute UserSignUpDto signupDto,
            ModelAndView modelAndView,
            BindingResult bindingResult
    ){

        if ((isUsernameExists(signupDto.username()))){
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (isEmailExists(signupDto.email())){
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (bindingResult.hasErrors()){
            modelAndView.setViewName("signup");
            return modelAndView;
        }


//        User users = User.builder()
//                .username(username)
//                .email(email)
//                .password(password)
//                .build();

//        userDao.save(user);

        userService.saveUser(signupDto);

        modelAndView.addObject("user", userDao.getUserByUsername(signupDto.username()));
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping ("/login")
    public ModelAndView login(@ModelAttribute UserLoginDto userLoginDto,
                              ModelAndView modelAndView
    )  {

        User user = getUserByUsernameAndPassword(userLoginDto.username(), userLoginDto.password());

        if (user != null) {
            modelAndView.setViewName("show-todo");
        } else {
            modelAndView.setViewName("login-error");
        }

        return modelAndView;
    }



    private boolean isUsernameExists(String username){
        try {
            User existingUser = userService.getByUsername(username);
            return existingUser != null;
        } catch (Exception e) {
            return false;
        }
    }

    private User getUserByUsernameAndPassword(String username, String password) {
        try {

            User user = userService.getByUsername(username);
            System.out.println("user = " + user.toString());
            System.out.println("user = " + user == null);

            if (user != null && user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private boolean isEmailExists(String email) {
        try {
            User existingUser = userDao.getByEmail(email);
            return existingUser != null;
        } catch (Exception e) {
            return false;
        }
    }



}
