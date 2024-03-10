package uz.pdp.todo_project.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.pdp.todo_project.dao.UserDao;
import uz.pdp.todo_project.domain.User;


@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final User user = userDao.getUserByUsername(username);
            return new UserContext(user);
        } catch (DataAccessException e) {
            log.error("{}", e.getMessage());
            throw new UsernameNotFoundException("User not found with username - " + username);
        }
    }

}
