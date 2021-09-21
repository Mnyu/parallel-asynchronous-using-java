package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import com.learnjava.util.CommonUtil;
import com.learnjava.util.LoggerUtil;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class GithubJobsClient {

    private WebClient webClient;

    public GithubJobsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubPosition> invokeGithubJobsAPIWithPageNumber(int pageNo, String description) {

        String uriString = UriComponentsBuilder.fromUriString("/positions.json")
                .queryParam("description", description)
                .queryParam("page", pageNo)
                .buildAndExpand()
                .toUriString();

        LoggerUtil.log("Uri : " + uriString);
        List<GitHubPosition> gitHubPositions = webClient
                .get()
                .uri(uriString)
                .retrieve()
                .bodyToFlux(GitHubPosition.class)
                .collectList()
                .block();

        return gitHubPositions;
    }

    public List<GitHubPosition> invokeGithubJobsAPIWithMultiplePageNumbers(List<Integer> pageNos, String description) {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        List<GitHubPosition> gitHubPositions = pageNos.stream()
                .map(pageNo -> invokeGithubJobsAPIWithPageNumber(pageNo, description))
                .flatMap(ghPositions -> ghPositions.stream())
                .collect(Collectors.toList());
        CommonUtil.timeTaken();
        return gitHubPositions;
    }

    public List<GitHubPosition> invokeGithubJobsAPIWithMultiplePageNumbersAsync(List<Integer> pageNos, String description) {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();

        List<CompletableFuture<List<GitHubPosition>>> gitHubPositionsCFs = pageNos.stream()
                .map(pageNo -> CompletableFuture.supplyAsync(() -> invokeGithubJobsAPIWithPageNumber(pageNo, description)))
                .collect(Collectors.toList());

        List<GitHubPosition> gitHubPositions = gitHubPositionsCFs.stream()
                .map(CompletableFuture::join)
                .flatMap(ghPositions -> ghPositions.stream())
                .collect(Collectors.toList());

        CommonUtil.timeTaken();
        return gitHubPositions;
    }

    public List<GitHubPosition> invokeGithubJobsAPIWithMultiplePageNumbersAsyncApproach2(List<Integer> pageNos, String description) {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();

        List<CompletableFuture<List<GitHubPosition>>> gitHubPositionsCFs = pageNos.stream()
                .map(pageNo -> CompletableFuture.supplyAsync(() -> invokeGithubJobsAPIWithPageNumber(pageNo, description)))
                .collect(Collectors.toList());

        CompletableFuture<Void> voidCF = CompletableFuture.allOf(gitHubPositionsCFs.toArray(new CompletableFuture[0]));

        // The below method will only get executed when all the CFs of gitHubPositionsCFs are completed.
        // Then why are we using join() [blocking call] below as well?
        // The reason is though CF is completed, but we want to get the result out of the CF, that is why join().
        List<GitHubPosition> gitHubPositions = voidCF.thenApply(v -> gitHubPositionsCFs.stream()
                        .map(CompletableFuture::join)
                        .flatMap(ghPositions -> ghPositions.stream())
                        .collect(Collectors.toList()))
                .join();

        CommonUtil.timeTaken();
        return gitHubPositions;
    }
}
