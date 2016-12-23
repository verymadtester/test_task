package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("on test method " +  getTestMethodName(result) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("on test method " + getTestMethodName(result) + " success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("on test method " + getTestMethodName(result) + " failure");
        File file = new File("");
        Reporter.setCurrentTestResult(result);
        System.out.println(file.getAbsolutePath());
        Reporter.log("screenshot saved at "+file.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg");
        Reporter.log("<a href='file://"+file.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg'>Screenshot</a>");
        CaptureScreenShot(result);
        Reporter.setCurrentTestResult(null);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("test method " + getTestMethodName(result) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("test failed but within success % " + getTestMethodName(result));
        File file = new File("");
        Reporter.setCurrentTestResult(result);
        System.out.println(file.getAbsolutePath());
        Reporter.log("screenshot saved at "+file.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg");
        Reporter.log("<a href='file://"+file.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg'>Screenshot</a>");
        CaptureScreenShot(result);
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("on start of test " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("on finish of test " + context.getName());
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    public void CaptureScreenShot(ITestResult result){
        Object obj  = result.getInstance();
        WebDriver driver = WebDiverConfig.setFirefoxPofile();
        File file = new File("");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File(file.getAbsolutePath()+"\\reports\\"
                    + result.getName()+".jpg"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
