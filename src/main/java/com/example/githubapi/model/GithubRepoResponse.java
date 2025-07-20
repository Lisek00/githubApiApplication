package com.example.githubapi.model;

import java.util.List;

public record GithubRepoResponse(
        String repositoryName,
        String ownerLogin,
        List<GithubBranchResponse> branchResponseList
) {}
