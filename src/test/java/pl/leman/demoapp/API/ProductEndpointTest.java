package pl.leman.demoapp.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.leman.demoapp.DemoappApplicationTests;
import pl.leman.demoapp.domain.*;

import static org.assertj.core.api.Assertions.*;

public class ProductEndpointTest extends DemoappApplicationTests {

    @Autowired
    ProductFacade productFacade;

    @Test
    public void shouldGetExistingProduct() {
        //given
        ProductRequestDto productRequestDto = new ProductRequestDto("product", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct = productFacade.create(productRequestDto);
        final String url = "http://localhost:" + port + "/products/" + createdProduct.getId();

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualToComparingFieldByField(createdProduct);
    }

    @Test
    public void shouldGetExistingProducts() {
        //given
        ProductRequestDto productRequestDto = new ProductRequestDto("product", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct = productFacade.create(productRequestDto);
        ProductRequestDto productRequestDto2 = new ProductRequestDto("product1", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct2 = productFacade.create(productRequestDto2);
        final String url = "http://localhost:" + port + "/products/";

        //when
        ResponseEntity<ProductListResponseDto> result = httpClient.getForEntity(url, ProductListResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getProducts().stream()
                .filter(productResponseDto -> productResponseDto.getId().equals(createdProduct2.getId()))).isNotEmpty();
        assertThat(result.getBody().getProducts().stream()
                .filter(productResponseDto -> productResponseDto.getId().equals(createdProduct.getId()))).isNotEmpty();
        assertThat(result.getBody().getProducts().size()).isEqualTo(2);
    }

    @Test
    public void shouldGetNotExistingProduct() {
        //given
        final String url = "http://localhost:" + port + "/products/" + "cos";

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void shouldCreateProduct() {
        //given
        final String url = "http://localhost:" + port + "/products";
        final ProductRequestDto product = new ProductRequestDto("cos", "10.0", "PLN", "ho.pl", "hot");
        String productJson = mapToJson(product);
        //when
        ResponseEntity<ProductResponseDto> result = httpClient.postForEntity(url, getHttpRequest(productJson), ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("cos");

    }

    @Test
    public void shouldDeleteProduct() {
        //given
        ProductRequestDto productRequestDto = new ProductRequestDto("product", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct = productFacade.create(productRequestDto);
        ProductRequestDto productRequestDto1 = new ProductRequestDto("product1", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct1 = productFacade.create(productRequestDto1);
        final String url = "http://localhost:" + port + "/products/" + createdProduct1.getId();

        //when
        httpClient.delete(url);
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(createdProduct1);
    }

    @Test
    public void shouldUpdateProduct() {
        //given
        ProductRequestDto productRequestDto = new ProductRequestDto("product", "10.0", "PLN", "po.pl", "hot");
        ProductResponseDto createdProduct = productFacade.create(productRequestDto);
        ProductRequestDto product = new ProductRequestDto("cos", "10.0", "PLN", "po.pl", "hot");
        String productJson = mapToJson(product);
        final String url = "http://localhost:" + port + "/products/" + createdProduct.getId();

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.exchange(url, HttpMethod.PUT, getHttpRequest(productJson), ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(createdProduct.getName());
    }

    String mapToJson(ProductRequestDto productRequestDto) {
        try {
            return objectMapper.writeValueAsString(productRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getHttpRequest(String json) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", "application/json");
        return new HttpEntity<>(json, httpHeaders);
    }

}
