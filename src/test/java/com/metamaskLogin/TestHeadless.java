package com.metamaskLogin;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestHeadless {
    
    @Test
    public void login() throws InterruptedException {
       
        // Path to the Chrome extension (.crx file)
        String extensionPath = "C:\\Users\\wbox62\\eclipse-workspace\\MetaMaskLoginUtility\\src\\main\\java\\lib\\MetaMaskChromeWebStore.crx";
        // Path to the user data directory
        String userDataDir = "C:\\Users\\wbox62\\Downloads\\ChromeUserProfile";

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(extensionPath));
        options.addArguments("user-data-dir=" + userDataDir);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        // Open the specified Chrome extension URL
        driver.get("chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/popup.html");

        Thread.sleep(3000);
        
//        Set<String> windowHandles = driver.getWindowHandles();
//        Iterator<String> itr = windowHandles.iterator();
//
//        if (windowHandles.size() > 1) {
//            // Multiple windows present
//            String mainHandle = driver.getWindowHandle();
//            for (String handle : windowHandles) {
//                if (!handle.equals(mainHandle)) {
//                    driver.switchTo().window(handle); // Switch to the second window
//                    break;
//                }
//            }
//        }

        
        try {
          
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']"))).sendKeys("Shubham@@11");
                driver.findElement(By.xpath("//button[text()='Unlock']")).click();
    
        } catch (Exception e) {
            System.out.println("Unlock button not found, proceeding with new wallet creation.");
        }  
             try {
            driver.findElement(By.xpath("//input[@type='checkbox']")).click();
            driver.findElement(By.xpath("//button[text()='Create a new wallet']")).click();
            driver.findElement(By.xpath("//input[@id='metametrics-opt-in']")).click();
            driver.findElement(By.xpath("//button[text()='I agree']")).click();
            driver.findElement(By.xpath("//input[@data-testid='create-password-new']")).sendKeys("Shubham@@11");
            driver.findElement(By.xpath("//input[@data-testid='create-password-confirm']")).sendKeys("Shubham@@11");
            driver.findElement(By.xpath("//input[@class='check-box far fa-square']")).click();
            driver.findElement(By.xpath("//button[text()='Create a new wallet']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[text()='Secure my wallet (recommended)']")).click();
            driver.findElement(By.xpath("//button[text()='Reveal Secret Recovery Phrase']")).click();

            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='recovery-phrase__chip chip chip--border-color-border-default chip--background-color-undefined chip--max-content']"));
            List<String> elementTexts = new ArrayList<>();

            Thread.sleep(3000);
            for (WebElement element : elements) {
                elementTexts.add(element.getText());
            }

            for (String text : elementTexts) {
                System.out.println(text);
            }

            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[text()='Next']")).click();
            Thread.sleep(3000);
            if (elementTexts.size() >= 8) {
                driver.findElement(By.xpath("//input[@data-testid='recovery-phrase-input-2']")).sendKeys(elementTexts.get(2));
                Thread.sleep(1000);
                driver.findElement(By.xpath("//input[@data-testid='recovery-phrase-input-3']")).sendKeys(elementTexts.get(3));
                Thread.sleep(1000);
                driver.findElement(By.xpath("//input[@data-testid='recovery-phrase-input-7']")).sendKeys(elementTexts.get(7));
                Thread.sleep(1000);
            } else {
                System.out.println("Not enough elements found to perform the actions.");
            }

            driver.findElement(By.xpath("//button[text()='Confirm']")).click();
            driver.findElement(By.xpath("//button[text()='Got it']")).click();
            driver.findElement(By.xpath("//button[@data-testid='pin-extension-next']")).click();
            driver.findElement(By.xpath("//button[@data-testid='pin-extension-done']")).click();
            Thread.sleep(3000);
             } catch (Exception e) {
                 System.out.println("Wallet creation elements not found or already present.");
             }
        }
    
}



