package uz.pdp.todo_project.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.todo_project.dao.UserDao;
import uz.pdp.todo_project.domain.User;
import uz.pdp.todo_project.dto.UserSignUpDto;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(final UserSignUpDto dto){
         userDao.save(
                User.builder()
                        .username(dto.username())
                        .email(dto.email())
                        .password(passwordEncoder.encode(dto.password()))
                        .build()
        );
    }

    public User getByUsername(String username){
        return userDao.getUserByUsername(username);
    }


}