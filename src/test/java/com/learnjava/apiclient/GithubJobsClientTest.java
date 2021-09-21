package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import com.learnjava.util.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GithubJobsClientTest {

    WebClient webClient = WebClient.create("https://jobs.github.com/");
    GithubJobsClient ghjClient = new GithubJobsClient(webClient);

    @Test
    void invokeGithubJobsAPIWithPageNumber() {
        // given
        int pageNo = 1;
        String description = "JavaScript";

        // when
        List<GitHubPosition> gitHubPositions = ghjClient.invokeGithubJobsAPIWithPageNumber(pageNo, description);
        LoggerUtil.log("gitHubPositions : " + gitHubPositions);

        // then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions.forEach(Assertions::assertNotNull);
    }

    @Test
    void invokeGithubJobsAPIWithMultiplePageNumbers() {
        // given
        List<Integer> pageNumList = List.of(1,2,3);
        String description = "Java";

        // when
        List<GitHubPosition> gitHubPositions = ghjClient.invokeGithubJobsAPIWithMultiplePageNumbers(pageNumList, description);
        LoggerUtil.log("gitHubPositions : " + gitHubPositions);

        // then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions.forEach(Assertions::assertNotNull);
    }

    @Test
    void invokeGithubJobsAPIWithMultiplePageNumbersAsync() {
        // given
        List<Integer> pageNumList = List.of(1,2,3);
        String description = "Java";

        // when
        List<GitHubPosition> gitHubPositions = ghjClient.invokeGithubJobsAPIWithMultiplePageNumbersAsync(pageNumList, description);
        LoggerUtil.log("gitHubPositions : " + gitHubPositions);

        // then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions.forEach(Assertions::assertNotNull);
    }
}