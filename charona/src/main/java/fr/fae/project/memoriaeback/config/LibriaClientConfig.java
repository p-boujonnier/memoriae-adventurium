package fr.fae.project.memoriaeback.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class LibriaClientConfig {

    @Value("${libria.base-url}")
    private String libriaBaseUrl;

    @Bean("libriaRestClient")
    public RestClient libriaRestClient() {
        return RestClient.builder()
                .baseUrl(libriaBaseUrl)
                .build();
    }
}
