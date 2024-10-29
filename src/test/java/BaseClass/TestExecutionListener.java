package BaseClass;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestExecutionListener implements ITestListener {
    public static int passedTestsCount = 0;
    public static int failedTestsCount = 0;
    public static int skippedTestsCount = 0;
    public static long startTime;
    public static long endTime;

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTestsCount++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedTestsCount++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedTestsCount++;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting Test Execution");
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Total Passed Tests: " + passedTestsCount);
        System.out.println("Total Failed Tests: " + failedTestsCount);
        System.out.println("Total Skipped Tests: " + skippedTestsCount);
        endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Total execution time: " + executionTime + " milliseconds");
    }

    public static void main(String[] args) {
    }
}
