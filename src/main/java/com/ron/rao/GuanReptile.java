package com.ron.rao;

import com.ron.rao.common.HttpClientUtils;
import com.ron.rao.model.VirusData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 冠状病毒实时信息爬虫类
 */
public class GuanReptile {

    public static void getInfoByChromeDriver(){
        String url = "https://voice.baidu.com/act/newpneumonia/newpneumonia";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);//写入你要抓取的网址
        WebElement webElement = webDriver.findElement(By.xpath("/html"));//获取页面全部信息
        WebElement tbody = webElement.findElement(By.tagName("tbody"));
        List<WebElement> trList = tbody.findElements(By.tagName("tr"));
        List<WebElement> tdList = null; // td列表
        List<WebElement> spanList = null; // span列表 获取省份名称
        List<VirusData> virusDataList = new ArrayList<>();
        WebElement td = null;
        VirusData virusData = null;  // 数据实体类
        for(WebElement tr : trList){
            tdList = tr.findElements(By.tagName("td"));
            virusData = new VirusData();
            //第一列是名字
            spanList = tdList.get(0).findElements(By.tagName("span"));
            virusData.setProvince(spanList.get(1).getText()); //省份名称是第二个字
            //新增
            virusData.setNewlyAdd(tdList.get(1).getText());
            //累计
            virusData.setTotal(tdList.get(2).getText());
            //治愈
            virusData.setCured(tdList.get(3).getText());
            //死亡
            virusData.setDeath(tdList.get(4).getText());
            System.out.println(virusData);
            virusDataList.add(virusData);
        }

    }



    public static void main(String[] args) {
        getInfoByChromeDriver();
    }
}
