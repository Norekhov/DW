package ru.skypro.homework.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.homework.constant.StaticForTests.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AdController adController;

    @MockitoSpyBean
    AdRepository adRepository;

    @Autowired
    CustomUserDetailsManager customUserDetailsManager;

    @MockitoSpyBean
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @MockitoSpyBean
    LoginController loginController;

    String token;

    @BeforeEach
    void init() {
        restTemplate.postForEntity("http://localhost:" + port + "/register", USER_ADMIN_REGISTER_DTO, ResponseEntity.class);
        restTemplate.postForEntity("http://localhost:" + port + "/register", USER_USER1_REGISTER_DTO, ResponseEntity.class);
    }

    @AfterEach
    void cleanDB() {
        adRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAllAds() {
        addAd();

        ResponseEntity<AdListDto> response = restTemplate.getForEntity("http://localhost:" + port + "/ads", AdListDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCount());
    }

    @Test
    void addAd() {
        CreateOrUpdateAdDto properties = new CreateOrUpdateAdDto();
        properties.setTitle("testTitle");
        properties.setDescription("testDescription");
        properties.setPrice(1000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("properties", properties);
        body.add("image", new FileSystemResource(IMAGE_JPG_PATH_STRING));

        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create("http://localhost:" + port + "/ads"));
        ResponseEntity<AdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD).exchange(request, AdDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addAdNegative() {
        CreateOrUpdateAdDto properties = new CreateOrUpdateAdDto();
        properties.setTitle("testTitle");
        properties.setDescription("testDescription");
        properties.setPrice(1000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("properties", properties);
        body.add("image", new FileSystemResource(IMAGE_JPG_PATH_STRING));

        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create("http://localhost:" + port + "/ads"));
        ResponseEntity<AdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, "p@55w0rd").exchange(request, AdDto.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getAdById() {

    }

    @Test
    void updateAd() {
    }

    @Test
    void removeAd() {
    }

    @Test
    void getUserAds() {
    }

    @Test
    void updateAdImage() {
    }
}