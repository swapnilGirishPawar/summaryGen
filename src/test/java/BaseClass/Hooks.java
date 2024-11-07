package BaseClass;

import Utils.CommonUtils;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.io.IOException;

public class  Hooks extends CommonUtils {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
        TestNG testNG = new TestNG();
        testNG.addListener(new TestExecutionListener());
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite() throws IOException, InterruptedException {
        System.out.println("After Suite");
        generateJson(passedTestsCount, failedTestsCount, skippedTestsCount);
    }
}
