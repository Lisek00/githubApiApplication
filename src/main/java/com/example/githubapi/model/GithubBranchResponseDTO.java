package com.example.githubapi.model;

import lombok.Data;
import lombok.Getter;

@Data
public class GithubBranchResponseDTO {
    private String name;
    private Commit commit;

    @Getter
    public static class Commit {
        private String sha;
    }
}
