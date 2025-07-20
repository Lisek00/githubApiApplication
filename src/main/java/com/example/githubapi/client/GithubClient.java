package com.example.githubapi.client;

import com.example.githubapi.exception.UserNotFoundException;
import com.example.githubapi.model.GithubBranchResponse;
import com.example.githubapi.model.GithubBranchResponseDTO;
import com.example.githubapi.model.GithubRepoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class GithubClient {
    private final RestTemplate restTemplate;

    public List<GithubRepoResponseDTO> getRepos(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        try {
            ResponseEntity<GithubRepoResponseDTO[]> response = restTemplate.getForEntity(url, GithubRepoResponseDTO[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(username);
        }
    }

    public List<GithubBranchResponse> getBranches(String user, String repo) {
        String url = "https://api.github.com/repos/" + user + "/" + repo + "/branches";
        ResponseEntity<GithubBranchResponseDTO[]> response = restTemplate.getForEntity(url, GithubBranchResponseDTO[].class);

        return Optional.ofNullable(response.getBody()).stream().flatMap(Arrays::stream)
                .map(branch -> new GithubBranchResponse(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }
}
