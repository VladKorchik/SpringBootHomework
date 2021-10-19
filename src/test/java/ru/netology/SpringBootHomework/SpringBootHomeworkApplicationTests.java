package ru.netology.SpringBootHomework;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.SpringBootHomework.Profile.DevProfile;
import ru.netology.SpringBootHomework.Profile.ProductionProfile;
import ru.netology.SpringBootHomework.Profile.SystemProfile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootHomeworkApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    public static GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    public static GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8080) + "/profile", String.class);
        SystemProfile waitingProfile = new DevProfile();
        Assert.assertEquals(forEntity.getBody(), waitingProfile.getProfile());
    }
    @Test
    void prodAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                prodApp.getMappedPort(8081) + "/profile", String.class);
        SystemProfile waitingProfile = new ProductionProfile();
        Assert.assertEquals(forEntity.getBody(), waitingProfile.getProfile());
    }

}