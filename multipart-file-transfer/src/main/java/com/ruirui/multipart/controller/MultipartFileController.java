package com.ruirui.multipart.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class MultipartFileController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello, " + (name == null ? "world" : name);
    }

    @PostMapping("/resumeUpload")
    @SuppressWarnings("deprecation")
    public String resumeUpload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multiFile = multiRequest.getFile("file1");

        String parameterName = multiFile.getName();
        String filename = multiFile.getOriginalFilename();
        File dest = new File("Dest.txt");
        multiFile.transferTo(dest);

        Assert.isTrue(parameterName.equals("file1"));
        Assert.isTrue(filename.equals("resume"));
        Assert.isTrue(dest.exists() == false, dest.getAbsolutePath() + " should not exist");

        return "success";
    }
}
