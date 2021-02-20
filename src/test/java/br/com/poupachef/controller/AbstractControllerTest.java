package br.com.poupachef.controller;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class AbstractControllerTest {

    @LocalServerPort
    protected int port;

    protected RequestSpecification specification;

    @BeforeEach
    public void setUp() {
        specification = new RequestSpecBuilder()
                .setPort(port)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    protected Header getAuthHeader() {
        return new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImV4cCI6MTYxNDYwNjkyMX0.H2T4m1-cwLRAVhamkgMcmHYHB6lSu9DEvoPPlJ70K48");
    }
}
