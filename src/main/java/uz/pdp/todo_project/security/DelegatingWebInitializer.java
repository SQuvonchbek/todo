package uz.pdp.todo_project.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class DelegatingWebInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }

}