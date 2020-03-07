package com.jxx.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class seleniumTest {

    @org.junit.Test
    public void Test() throws Exception{
        // 加载驱动
        System.setProperty("webdriver.gecko.driver", "/Users/dhs/Downloads/geckodriver");
//        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        FirefoxDriver driver = new FirefoxDriver();

        //页面加载超时时间设置为 5s
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        // 访问登陆页面
        driver.get("http://www.dhsstrive.com/");

        //定位对象时给 5s 的时间, 如果 5s 内还定位不到则抛出异常
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement eAction = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/a"));
        JavascriptExecutor driver1 = driver;
        driver1.executeScript("arguments[0].click();",eAction);


        Thread.sleep(10000L);
        driver.quit();


    }
}
