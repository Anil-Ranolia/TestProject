package testproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;



public class ProjectTest {
    static WebDriver driver;
    @BeforeClass
    public static void setWindow(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://tms.pisystindia.com/admin/login");
        driver.manage().window().maximize();
    }

    @Before
    public void login() {
        WebElement email = driver.findElement(By.id("admin_email"));
        WebElement password = driver.findElement(By.id("admin_password"));
        email.sendKeys("sales.infinitycorp@gmail.com");
        password.sendKeys("123456");
        WebElement submit = driver.findElement(By.xpath("(//button)[1]"));
        submit.click();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void logout() throws InterruptedException {
        WebElement infinity = driver.findElement(By.xpath("//span[@class=\"user-avatar\"]"));
        infinity.click();
        WebElement logout = driver.findElement(By.xpath("(//a//span)[9]"));
        logout.click();
        Thread.sleep(3000);
    }

    @Test
    public void changeListOrder(){
        WebElement projects = driver.findElement(By.xpath("(//a)[22]"));
        projects.click();
        try{
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        WebElement ini_count = driver.findElement(By.xpath("(//td)[1]"));
        String initial_count = String.valueOf(ini_count.getText());

        WebElement reverseNum = driver.findElement(By.xpath("(//th)[1]"));
        reverseNum.click();

        WebElement fin_count = driver.findElement(By.xpath("(//td)[1]"));
        String final_count = String.valueOf(fin_count.getText());

        if(!initial_count.equals(final_count)){
            System.out.println("The list is reversed");
        }
        else{
            System.out.println("The list is not reversed");
        }
    }

    @Test
    public void addProject() throws InterruptedException {
        WebElement projects = driver.findElement(By.xpath("(//a)[22]"));
        projects.click();
        WebElement addButton = driver.findElement(By.xpath("(//a[@type=\"submit\"])[1]"));
        addButton.click();
        WebElement projectName = driver.findElement(By.id("project_name"));
        WebElement projectCode = driver.findElement(By.id("project_code"));
        WebElement projectDescription = driver.findElement(By.id("project_description"));
        WebElement startDate = driver.findElement(By.id("start_date"));
        WebElement endDate = driver.findElement(By.id("end_date"));
        projectName.sendKeys("Automation Selenium");
        String projectCustomCode = "AUTOSEL" + (new Random().nextInt(800000) + 100);
        projectCode.sendKeys(projectCustomCode);
        projectDescription.sendKeys("This test is being run using Selenium");
        startDate.sendKeys("06122022");
        endDate.sendKeys("06282022");
        WebElement add = driver.findElement(By.xpath("//button"));
        add.click();
        Thread.sleep(3000);
        WebElement read = driver.findElement(By.xpath("(//td)[3]"));
        String readCode = String.valueOf(read.getText());

        if(readCode.equals(projectCustomCode)){
            System.out.println("Project Added successfully");
        }
        else{
            System.out.println("Project Adding failed");
        }
    }

    @Test
    public void updateProject() throws InterruptedException {
        WebElement projects = driver.findElement(By.xpath("(//a)[22]"));
        projects.click();
        WebElement read = driver.findElement(By.xpath("(//td)[11]"));
        String readCode = String.valueOf(read.getText());
        WebElement updateButton = driver.findElement(By.xpath("(//td//a)[2]"));
        updateButton.click();
        WebElement projectName = driver.findElement(By.id("project_name"));
        WebElement projectCode = driver.findElement(By.id("project_code"));
        WebElement projectDescription = driver.findElement(By.id("project_description"));
        projectName.clear();
        projectCode.clear();
        projectDescription.clear();
        int num = (new Random().nextInt(9000) + 100);
        projectName.sendKeys("NewUsingSelenium" + num);
        String projectCustomCode = "UPDATED" + num;
        projectCode.sendKeys(projectCustomCode);
        projectDescription.sendKeys("This test is being run using Selenium and it is updated later using Id " + projectCustomCode);
        WebElement update = driver.findElement(By.xpath("//button"));
        update.click();
        Thread.sleep(3000);
        WebElement readUpdated = driver.findElement(By.xpath("(//td)[11]"));
        String readCodeUpdated = String.valueOf(readUpdated.getText());

        if(!readCodeUpdated.equals(readCode)){
            System.out.println("Project Updated successfully");
        }
        else{
            System.out.println("Project Updating failed");
        }
    }

    @AfterClass
    public static void shut(){
        driver.quit();
    }
}
