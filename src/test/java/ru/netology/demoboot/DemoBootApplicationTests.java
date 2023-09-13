package ru.netology.demoboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoBootApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private final GenericContainer<?> prodApp = new GenericContainer<>("prodvapp")
            .withExposedPorts(8081);

    @BeforeEach
    public void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devAppForEntity = restTemplate.getForEntity
                ("http://localhost:" + devApp.getMappedPort(8080), String.class);
        ResponseEntity<String> prodAppForEntity = restTemplate.getForEntity
                ("http://localhost:" + prodApp.getMappedPort(8080), String.class);

        Assertions.assertEquals("Current profile is dev", devAppForEntity.getBody());
        Assertions.assertEquals("Current profile is production", prodAppForEntity.getBody());
    }
}
