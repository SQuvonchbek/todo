package uz.pdp.todo_project.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;


@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

//        String message = exception.getMessage();



//        System.out.println(message);

//        System.out.println("print stackTrace = " + Arrays.toString(exception.getStackTrace()));

//        exception.printStackTrace();

        response.sendRedirect("/auth/login?error=%s".formatted(exception.getMessage()));

    }
}