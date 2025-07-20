package com.example.githubapi;

import com.example.githubapi.model.GithubRepoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnNonForkReposWithBranches() {
		//given
		String username = "octocat";

		//when
		ResponseEntity<GithubRepoResponse[]> response = restTemplate.getForEntity("/github/" + username, GithubRepoResponse[].class);

		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();

		GithubRepoResponse repo = response.getBody()[0];
		assertThat(repo.repositoryName()).isNotBlank();
		assertThat(repo.ownerLogin()).isEqualTo(username);
		assertThat(repo.branchResponseList()).isNotEmpty();
	}

}
