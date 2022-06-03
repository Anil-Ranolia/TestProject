package testproject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtendReportBase {
    public static ExtentReports reports;
    public static ExtentTest test;

    public static void createReport()
    {
        String path = System.getProperty("user.dir")+"\\test-output\\extent-report.html";
        System.out.println(path);
        reports = new ExtentReports(path);


    }
}