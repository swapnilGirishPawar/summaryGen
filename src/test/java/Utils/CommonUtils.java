package Utils;

import BaseClass.TestExecutionListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils extends TestExecutionListener {
    public static void generateJson(int passedTestsCount, int failedTestsCount, int skippedTestsCount) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Create a HashMap to represent the JSON structure
            Map<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("reportName", "TestNG Summary Report");
            jsonObject.put("testRuns", "[]");

            // Create a nested Map for statistics
            Map<String, Integer> statistic = new HashMap<>();
            statistic.put("failed", failedTestsCount);
            statistic.put("broken", 0);
            statistic.put("skipped", skippedTestsCount);
            statistic.put("passed", passedTestsCount);
            statistic.put("unknown", 0);
            statistic.put("total", failedTestsCount + passedTestsCount + skippedTestsCount);
            jsonObject.put("statistic", statistic);

            // Create a nested Map for time
            Map<String, Integer> time = new HashMap<>();
            time.put("start", 0);
            time.put("stop", 0);
            time.put("duration", 0);
            time.put("minDuration", 0);
            time.put("maxDuration", 0);
            time.put("sumDuration", 0);
            jsonObject.put("time", time);

            // Write the JSON to a file
            File reportFile = new File("reports/summary.json");
            mapper.writeValue(reportFile, jsonObject);

            System.out.println("JSON file generated successfully!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateJson(passedTestsCount, failedTestsCount, skippedTestsCount);
    }
}
