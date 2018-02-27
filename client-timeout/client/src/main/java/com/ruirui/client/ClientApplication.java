package com.ruirui.client;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ClientApplication {

    private final Logger log = LoggerFactory.getLogger(ClientApplication.class);

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(500, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .build();

    @Value("${users.url.add}")
    String addUrl;

    @Bean
    public CommandLineRunner postUserCommand() {
        return args -> {
            final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            String content = "{\"name\": \"吴\", \"age\": 29}";
            RequestBody body = RequestBody.create(mediaType, content);

            Request request = new Request.Builder().url(addUrl).post(body).build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (SocketTimeoutException e) {
                log.error("客户端超时，但服务端的数据还是成功插入了", e);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
