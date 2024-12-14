package ru.skypro.homework.controller;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static ru.skypro.homework.constant.StaticForTests.*;

@Sql(scripts = {"classpath:schema.sql", "classpath:test-data.sql"}, executionPhase = BEFORE_TEST_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdControllerTest {

    //    @TestConfiguration
//    static class TestRestTemplateAuthenticationConfiguration {
//        @Bean
//        public RestTemplateBuilder restTemplateBuilder() {
//            return new RestTemplateBuilder().basicAuthentication(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD);
//        }
//    }
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

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/";
//        restTemplate.postForLocation(baseUrl + "logout",new LoginDto(USER_ADMIN_PASSWORD,USER_ADMIN_EMAIL));
        SecurityContextHolder.createEmptyContext();
//        restTemplate = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD);
//        BasicAuthenticationInterceptor bai = new BasicAuthenticationInterceptor(USER_USER1_EMAIL, USER_USER1_PASSWORD);
//        restTemplate.getRestTemplate().getInterceptors().add(bai);
//        restTemplate.postForEntity(baseUrl + "register"
//                , USER_ADMIN_REGISTER_DTO, ResponseEntity.class);
//        restTemplate.postForEntity(baseUrl + "register"
//                , USER_USER1_REGISTER_DTO, ResponseEntity.class);
    }

    @AfterEach
    void cleanDB() {
        adRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @Order(1)
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
        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(baseUrl + "ads"));
        ResponseEntity<AdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD).exchange(request, AdDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(adService, Mockito.times(1)).addAd(Mockito.any(), Mockito.any());
    }

    @Test
    void addAdNegative() {
        CreateOrUpdateAdDto properties = new CreateOrUpdateAdDto();
        properties.setTitle("testTitle");
        properties.setDescription("testDescription");
        properties.setPrice(1000);
        restTemplate.postForLocation(baseUrl + "login", new LoginDto(USER_ADMIN_PASSWORD, USER_ADMIN_EMAIL));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("properties", properties);
        body.add("image", new FileSystemResource(IMAGE_JPG_PATH_STRING));

        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(baseUrl + "ads"));
        ResponseEntity<AdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, "p@55w0rd").exchange(request, AdDto.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void getAllAds() {
        ResponseEntity<AdListDto> response = restTemplate.getForEntity(baseUrl + "ads", AdListDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().getCount());
        Mockito.verify(adService, Mockito.times(1)).getAllAds();
    }

    @Test
    void getAdUnauthorized() {
        ResponseEntity<ExtendedAdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, "WRONGPassword").getForEntity(baseUrl + "ads/" + 1, ExtendedAdDto.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getUserAds() {
        ResponseEntity<ExtendedAdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD).getForEntity(baseUrl + "ads/me", ExtendedAdDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(adService, Mockito.times(1)).getUserAds();
    }

    @Test
    @PreAuthorize("authenticated")
    void getAdById() {
        ResponseEntity<ExtendedAdDto> response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD).getForEntity(baseUrl + "ads/" + 1, ExtendedAdDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(adService, Mockito.times(1)).getAdById(1);
    }

    @Test
    void updateAd() {
        AdDto ad = AdMapper.toDto(adRepository.findById(1).orElseThrow());
        int oldPrice = ad.getPrice();
        ad.setPrice(oldPrice + 1);
        AdDto response = restTemplate.withBasicAuth(USER_ADMIN_EMAIL, USER_ADMIN_PASSWORD).patchForObject(baseUrl + "ads/" + 1, ad, AdDto.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(adService, Mockito.times(1)).updateAd(1, Mockito.any());
    }

    @Test
    void removeAd() {

    }

    @Test
    void updateAdImage() {
    }
}