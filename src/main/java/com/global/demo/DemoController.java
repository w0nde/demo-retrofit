package com.global.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;

@RestController
public class DemoController {

    private GithubApi githubApi;

    public DemoController(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @GetMapping("/users")
    List<User> get() throws IOException {
        return githubApi.users().execute().body();
    }

}
