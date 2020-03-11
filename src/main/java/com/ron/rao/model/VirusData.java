package com.ron.rao.model;

import lombok.Data;

@Data
public class VirusData {


    private String province; //省份
    private String newlyAdd; //新增数量
    private String total; //累计
    private String cured; //治愈
    private String death; //死亡


    @Override
    public  String toString(){
        return "省份："+ province +",今日新增："+ newlyAdd + "，累计数量："+ total+
                "，累计治愈：" + cured + "，累计死亡：" + death;
    }
}
