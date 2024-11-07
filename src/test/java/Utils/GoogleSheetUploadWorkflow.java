package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class GoogleSheetUploadWorkflow {

    private final String logger;
    private final String githubToken;
    private static final String SHEET_TARGET_BRANCH = "main";

    public GoogleSheetUploadWorkflow(String logger, String githubToken) {
        this.logger = logger;
        this.githubToken = "";
    }

    public HttpResponse<String> triggerGoogleSheetUploadWorkflow(Map<String, String> results) throws IOException, InterruptedException {

        log("Project is " + results.get("projectCode") + " so process the hook...");
        log("Make the call to the GitHub Action Workflow with runId " + results.get("runId"));

        String base64Results = Base64.getEncoder().encodeToString(new ObjectMapper().writeValueAsString(results).getBytes());
        Map<String, String> body = new HashMap<>();
        body.put("ref", SHEET_TARGET_BRANCH == null ? "main" : SHEET_TARGET_BRANCH);
        body.put("inputs", "{\"results\":\"" + base64Results + "\"}");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/Adaptavant/google-sheet-reporter-action/actions/workflows/google-sheet-reporter-service-wd.yml/dispatches"))
                .method("POST", HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(body)))
                .headers("Accept", "application/vnd.github.v3+json",
                        "Authorization", "token " + githubToken,
                        "X-GitHub-Api-Version", "2022-11-28")
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Body -> "+body);
        System.out.println("response -> "+response.toString());
        return response;
    }

    private void log(String message) {
        System.out.println(logger + message); // Replace with your actual logging implementation
    }

    // Replace this with your actual JSON library (e.g., Jackson)

    // Define a static variable for the target branch if applicable

}
