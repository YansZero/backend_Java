package com.yanszero.ytdemo.oil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanszero.ytdemo.common.result.Result;
import com.yanszero.ytdemo.common.result.ResultCodeEnum;
import com.yanszero.ytdemo.model.oil.OilSet;
import com.yanszero.ytdemo.oil.service.OilSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "油價基本資訊")
@CrossOrigin
@RestController
@RequestMapping("/base_info/oil/oilService")
public class OilSetController {

    // 最終網址: http://localhost:8085/base_info/oil/oilService/getOil
    //注入SERVICE
    @Autowired
    private OilSetService oilSetService;

    // 測試是否連上
    @ApiOperation(value = "獲取油價全部資訊")
    @GetMapping("getOil")
    public Result findOilSet(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("price_date");
        List<OilSet> list = oilSetService.list(queryWrapper);
        return Result.ok(list);
    }

    @ApiOperation(value = "刪除油價資料")
    @DeleteMapping("deleteOil{price_date}")
    public Result removeOilSet(@PathVariable String price_date) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        boolean flag = false;
        String errMsg = "";
        Date para = null;
        try {
            para = new SimpleDateFormat("yyyy-MM-dd").parse(price_date);
            queryWrapper.eq("price_date", para);
            flag = oilSetService.remove(queryWrapper);
        }
        catch (ParseException e){
            flag = false;
            errMsg = ",conver DATE ERROR["+ price_date +"],message["+ e.getMessage() +"]";
        };

        if (flag) {
            return Result.ok();
        }
        else {
            Result bad_req = new Result<>();
            bad_req.code(202);
            bad_req.message(ResultCodeEnum.PARAM_ERROR.getMessage() + errMsg);
            return bad_req;
        }

    }
}
