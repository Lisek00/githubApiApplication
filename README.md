# githubApiApplication

A simple Spring Boot application that fetches public, non-fork GitHub repositories for a given user, including branch names and last commit SHA.

## Features

- Fetch public GitHub repos (non-forks only)
- Return branch names with last commit SHA
- Return 404 if user doesn't exist
- Integration tested
- Uses GitHub REST API v3

## How to Run

```bash
./mvnw spring-boot:run
