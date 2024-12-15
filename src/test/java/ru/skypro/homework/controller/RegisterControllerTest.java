package ru.skypro.homework.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CustomUserDetailsManager;
import ru.skypro.homework.service.impl.CustomUserDetailsManagerImpl;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static ru.skypro.homework.constant.StaticForTests.*;

//@Sql(scripts = {"classpath:schema.sql", "classpath:test-data.sql"}, executionPhase = BEFORE_TEST_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterControllerTest {

    @LocalServerPort
    int port;
    String baseUrl;


    @Autowired
    TestRestTemplate restTemplate;

    @MockitoSpyBean
    AdService adService;

    @MockitoSpyBean
    AdRepository adRepository;

    @MockitoSpyBean
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsManagerImpl customUserDetailsManagerImpl;

    @Autowired
    private UserController userController;

    @MockitoSpyBean
    private AdController adController;

    @Autowired
    private CommentRepository commentRepository;

    @MockitoSpyBean
    private RegisterController registerController;

    @MockitoSpyBean
    CustomUserDetailsManager userService;

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/";
    }

    @AfterEach
    void cleanDB() {
        adRepository.deleteAll();
        userRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    void register() {
        Assertions.assertEquals(userRepository.findAll().size(), 0);
        restTemplate.postForLocation(baseUrl + "register", new RegisterDto(
                USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD, USER_ADMIN_FIRST_NAME
                ,USER_ADMIN_LAST_NAME,USER_ADMIN_PHONE,USER_ADMIN_ROLE));
        User userFromDb=userRepository.findByUsername(USER_ADMIN_EMAIL).orElseThrow();
        Assertions.assertEquals(1, userRepository.findAll().size());
        Assertions.assertEquals(USER_ADMIN_EMAIL, userFromDb.getUsername());
        Assertions.assertEquals(USER_ADMIN_FIRST_NAME, userFromDb.getFirstName());
        Assertions.assertEquals(USER_ADMIN_LAST_NAME, userFromDb.getLastName());
        Assertions.assertEquals(USER_ADMIN_PHONE, userFromDb.getPhone());
        Assertions.assertEquals(USER_ADMIN_ROLE, userFromDb.getRole());
        Assertions.assertNotEquals(USER_ADMIN_PASSWORD,userFromDb.getPassword());

        Mockito.verify(registerController,Mockito.times(1)).register(Mockito.any());
        Mockito.verify(userService,Mockito.times(1)).createUser(Mockito.any(RegisterDto.class));
    }


}