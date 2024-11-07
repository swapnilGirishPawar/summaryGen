package testSuite;

import org.testng.Assert;
import org.testng.annotations.*;


public class testFileOne extends BaseClass.Hooks {

    @Test
    public void test1() {
        System.out.println("Test 1");
//        Assert.fail("intentionally failing the test");
    }

    @Test
    public void test2() {
        System.out.println("Test 2");
    }

    @Test
    public void test3() {
        System.out.println("Test 3");
//        Assert.fail("intentionally failing the test");
    }

    @Test
    public void test4() {
        System.out.println("Test 4");
    }

}
