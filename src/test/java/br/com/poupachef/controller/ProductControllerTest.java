package br.com.poupachef.controller;

import br.com.poupachef.dto.request.ProductRequest;
import br.com.poupachef.dto.response.ProductResponse;
import br.com.poupachef.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
class ProductControllerTest extends AbstractControllerTest {

    @MockBean
    private ProductService productService;

    @Test
    void whenGetAll_thenShouldReturnProducts() {
        Page<ProductResponse> productResponse = new PageImpl<>(
                Arrays.asList(
                        ProductResponse.builder().id(1L).build(),
                        ProductResponse.builder().id(2L).build()));

        when(productService.getAll(any(), any())).thenReturn(productResponse);

        with().spec(specification)
                .when()
                .header(getAuthHeader())
                .get("/products")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body("content.size()", equalTo(2));
    }

    @Test
    void givenInvalidBody_whenCreate_thenThrowBadRequest() {
        ProductRequest productRequest = ProductRequest.builder().build();
        with().spec(specification)
                .when()
                .header(getAuthHeader())
                .body(productRequest)
                .post("/products")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
