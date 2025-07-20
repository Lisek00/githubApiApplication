package com.example.githubapi.service;

import com.example.githubapi.client.GithubClient;
import com.example.githubapi.model.GithubRepoResponse;
import com.example.githubapi.model.GithubRepoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final GithubClient githubClient;

    public List<GithubRepoResponse> getNonForkReposWithBranches(String username) {
        List <GithubRepoResponseDTO> repos = githubClient.getRepos(username);
        return repos.stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> new GithubRepoResponse(
                        repo.getName(),
                        repo.getOwner().getLogin(),
                        githubClient.getBranches(username, repo.getName())
                ))
                .toList();
    }
}
