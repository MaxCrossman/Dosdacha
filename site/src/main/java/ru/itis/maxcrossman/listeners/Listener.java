package ru.itis.maxcrossman.listeners;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.maxcrossman.repositories.*;
import ru.itis.maxcrossman.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/DB");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        PagesRepository pagesRepository = new PagesRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        PostsRepository postsRepository = new PostsRepositoryImpl(dataSource);

        PageListingService pageListingService = new PageListingServiceImpl(pagesRepository);
        PostListingService postListingService =
                new PostListingServiceImpl(postsRepository, usersRepository, pagesRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);

        servletContext.setAttribute("pageListingService", pageListingService);
        servletContext.setAttribute("postListingService", postListingService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("validator", validator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
