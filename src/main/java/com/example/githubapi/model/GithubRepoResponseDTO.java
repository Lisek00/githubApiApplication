package com.example.githubapi.model;

import lombok.Data;
import lombok.Getter;

@Data
public class GithubRepoResponseDTO {
    private String name;
    private Owner owner;
    private boolean fork;

    @Getter
    public static class Owner {
        private String login;
    }
}
