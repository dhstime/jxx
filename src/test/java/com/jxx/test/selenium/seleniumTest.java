package com.jxx.test.selenium;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class seleniumTest {

    @org.junit.Test
    public void Test() throws Exception{
        // 加载驱动
        System.setProperty("webdriver.gecko.driver", "/Users/dhs/Downloads/geckodriver");
//        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        FirefoxDriver driver = new FirefoxDriver();
        // 访问登陆页面
        driver.get("http://app1.sfda.gov.cn/datasearchcnda/face3/dir.html");
        //等待页面加载
        Thread.sleep(500);

//        List<WebElement> elementsByCssSelector = driver.findElementsByCssSelector("body > center > table:nth-child(19) > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child(2) > td > table > tbody");
//        WebElement webElement = elementsByCssSelector.get(0);

        //国产医疗器械产品（注册）
        WebElement chinaZc = driver.findElementByCssSelector("body > center > table:nth-child(19) > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(1) > a");
        String href = chinaZc.getAttribute("href");
        System.out.println(href);
        driver.get(href);
        //等待页面记载
        Thread.sleep(500);
        //子页面
        WebElement childElement = driver.findElementByCssSelector("#content > div > table:nth-child(2) > tbody");
        List<WebElement> tr = childElement.findElements(By.tagName("tr"));
        for (WebElement webElement : tr) {
            String title = webElement.getText();
            if(StringUtils.isBlank(title)){
                continue;
            }
            String href1 = webElement.getAttribute("href");
            String a = webElement.getTagName();
            System.out.println("子页面标题"+title);
            System.out.println("标题内链接"+a);
        }
        driver.quit();


    }
}
