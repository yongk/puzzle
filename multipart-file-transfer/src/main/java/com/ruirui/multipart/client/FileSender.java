package com.ruirui.multipart.client;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileSender {

    static Logger logger = LoggerFactory.getLogger(FileSender.class);

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(500, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .build();


    public static void main(String[] args) throws IOException {
        sendTo(9000); // tomcat
        sendTo(9001); // jetty
    }

    private static void sendTo(int port) throws IOException {
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("foo", "bar")
                .addFormDataPart("file1", "resume", RequestBody.create(MediaType.parse("text/plain"), "Some text".getBytes()))
                .build();

        Request request = new Request.Builder().url("http://localhost:" + port + "/resumeUpload").post(body).build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("Status code: " + response.code());
            Assert.isTrue(response.code() == 200, "响应应该是成功的");
        }
    }
}
