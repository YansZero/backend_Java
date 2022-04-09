package com.yanszero.ytdemo.oil.controller;

import com.yanszero.ytdemo.model.oil.OilSet;
import com.yanszero.ytdemo.oil.service.OilSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/base_info/oil/oilSet")
public class OilSetController {

    //注入SERVICE
    @Autowired
    private OilSetService oilSetService;

    // 測試是否連上
    @GetMapping("findAll")
    public List<OilSet> findOilSet(){
        List<OilSet> list = oilSetService.list();
        System.out.println("oil"+ list);
        return list;
    }
}
