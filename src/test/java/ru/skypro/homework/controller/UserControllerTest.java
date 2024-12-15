package ru.skypro.homework.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.CustomUserDetailsManagerImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@Sql(scripts = {"classpath:schema.sql", "classpath:test-data.sql"}, executionPhase = BEFORE_TEST_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

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

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/";
    }

    @Test
    void setPassword() {
    }

    @Test
    void getUser() {

    }

    @Test
    void updateUser() {
    }

    @Test
    void updateUserAvatar() {
    }
}