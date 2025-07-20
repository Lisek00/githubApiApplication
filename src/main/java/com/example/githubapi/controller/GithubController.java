package com.example.githubapi.controller;

import com.example.githubapi.model.GithubRepoResponse;
import com.example.githubapi.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github")
public class GithubController {
    private final GithubService service;

    @GetMapping("/{username}")
    public List<GithubRepoResponse> getUserRepos(@PathVariable String username) {
        return service.getNonForkReposWithBranches(username);
    }
}
