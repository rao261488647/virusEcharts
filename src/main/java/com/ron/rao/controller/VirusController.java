package com.ron.rao.controller;


import com.alibaba.fastjson.JSONObject;
import com.ron.rao.common.BaseController;
import com.ron.rao.model.VirusData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("virusController")
@RequestMapping("virus")
public class VirusController extends BaseController {


    @RequestMapping("index")
    public String index(){

        return "/echarts/index";
    }


    @ResponseBody
    @RequestMapping("echarts")
    public String getEcharts(){
        Map<String,String> resultMap = new HashMap<>();
//        List<String> titleList = new ArrayList<>(); //作为报表筛选条件
//        titleList.add("新增确诊");
//        titleList.add("累计确诊");
//        titleList.add("治愈");
//        titleList.add("死亡");

        String code = "1";
        String msg = "获取成功";

        List<String[]> dataStrList = getInfoByChromeDriver();

        String jsonStr = JSONObject.toJSONString(dataStrList);
        System.out.println(jsonStr);
        resultMap.put("code",code);
        resultMap.put("msg ",msg);
        resultMap.put("data",jsonStr);
        return JSONObject.toJSONString(resultMap);
    }


    private static  List<String[]> getInfoByChromeDriver(){
        String url = "https://voice.baidu.com/act/newpneumonia/newpneumonia";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);//写入你要抓取的网址
        WebElement webElement = webDriver.findElement(By.xpath("/html"));//获取页面全部信息
        WebElement tbody = webElement.findElement(By.tagName("tbody"));
        List<WebElement> trList = tbody.findElements(By.tagName("tr"));
        List<WebElement> tdList = null; // td列表
        List<WebElement> spanList = null; // span列表 获取省份名称
        String title[] = new String[]{"类型","新增确诊","累计确诊","治愈","死亡"};
        VirusData virusData = null;  // 数据实体类
        String dataStr[] = null; //展示数据
        List<String[]> dataStrList = new ArrayList<>(); //展示数据集合
        dataStrList.add(title);
        for(WebElement tr : trList){
            dataStr = new String[5];
            tdList = tr.findElements(By.tagName("td"));
            virusData = new VirusData();
            //第一列是名字
            spanList = tdList.get(0).findElements(By.tagName("span"));
            virusData.setProvince(spanList.get(1).getText()); //省份名称是第二个字
            dataStr[0] = spanList.get(1).getText();
            //新增
            virusData.setNewlyAdd(tdList.get(1).getText());
            dataStr[1] = tdList.get(1).getText();
            //累计
            virusData.setTotal(tdList.get(2).getText());
            dataStr[2] = tdList.get(2).getText();
            //治愈
            virusData.setCured(tdList.get(3).getText());
            dataStr[3] = tdList.get(3).getText();
            //死亡
            virusData.setDeath(tdList.get(4).getText());
            dataStr[4] = tdList.get(4).getText().equals("-") ? "0" :tdList.get(4).getText();
            dataStrList.add(dataStr);
//            System.out.println(virusData);
        }
        return dataStrList;
    }

}
