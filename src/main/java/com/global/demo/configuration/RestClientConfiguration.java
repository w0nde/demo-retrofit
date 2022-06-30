package com.global.demo.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.global.demo.api.GithubApi;
import com.global.demo.api.RickAndMortyApi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@Slf4j
public class RestClientConfiguration {


    @Bean
    GithubApi githubApi(EndpointsConfig.Endpoint githubEndpoint) {
        return retroFitConfiguration(githubEndpoint).create(GithubApi.class);
    }

    @Bean
    RickAndMortyApi rickAndMortyApi(EndpointsConfig.Endpoint rickandmortyEndpoint) {
        return retroFitConfiguration(rickandmortyEndpoint).create(RickAndMortyApi.class);
    }

    private Retrofit retroFitConfiguration(EndpointsConfig.Endpoint endpoint) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(endpoint.getBaseUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
                .build();
    }
    private ObjectMapper getObjectMapper() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new StdDateFormat())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS);
    }
}
