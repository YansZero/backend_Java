package com.yanszero.ytdemo.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanszero.ytdemo.common.result.Result;
import com.yanszero.ytdemo.common.result.ResultCodeEnum;
import com.yanszero.ytdemo.model.oil.OilSet;
import com.yanszero.ytdemo.oil.service.OilSetService;
import com.yanszero.ytdemo.vo.oil.OilSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "油價基本資訊")
@CrossOrigin
@RestController
@RequestMapping("/base_info/oil/oilService")
public class OilSetController {

    // 最終網址: http://localhost:8085/base_info/oil/oilService/getOil
    //注入SERVICE
    @Autowired
    private OilSetService oilSetService;

    Boolean hasPara = false;

    //
    @ApiOperation(value = "獲取油價全部資訊")
    @GetMapping("getOil")
    public Result findOilSet(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        // 預設日期最新的在最上面
        queryWrapper.orderByDesc("price_date");
        List<OilSet> list = oilSetService.list(queryWrapper);
        return Result.ok(list);
    }

    //條件查詢 帶分頁功能
    @ApiOperation(value = "條件查詢 帶分頁功能")
    @GetMapping("getOil/{pageinfo}")
    public Result getPageOilSet(@PathVariable Map<String, Object> pageInfo) {

        Map<String,Object> paraInfo = pageInfo;
        String keyword = "";
        long pageNow = 0;
        long pageSize = 10;

        if (paraInfo.size()>0) {
            if (paraInfo.containsKey("pageinfo")) {
                // 拆解兩層
                Map pinfo = JSONObject.parseObject(paraInfo.get("pageinfo").toString());
                if (pinfo.containsKey("page")) {
                    Map pagePara = JSONObject.parseObject(pinfo.get("page").toString());
                    pageNow = Long.parseLong(pagePara.get("pageNow").toString());
                    pageSize = Long.parseLong(pagePara.get("pageSize").toString());
                }
                if (pinfo.containsKey("keyword")) {
                    keyword = pinfo.get("keyword").toString();
                }
          }
        }
        // 建立PAGE對象 傳遞當前頁每頁筆數
        Page<OilSet> page = new Page<>(pageNow,pageSize);

        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("price_date",keyword);
            queryWrapper.or();
            queryWrapper.like("oil_92",keyword);
            queryWrapper.or();
            queryWrapper.like("oil_95",keyword);
            queryWrapper.or();
            queryWrapper.like("oil_98",keyword);
            queryWrapper.or();
            queryWrapper.like("diesel_fuel",keyword);
        }
        //調用方法實現功能
        Page<OilSet> pageOilSet = oilSetService.page(page,queryWrapper);

        return Result.ok(pageOilSet);
    }

    //條件查詢 帶分頁功能
//    @ApiOperation(value = "條件查詢 帶分頁功能")
//    @PostMapping("getOil/{current}/{limit}")
//    public Result getPageOilSet2(@PathVariable long current,
//                                 @PathVariable long limit,
//                                 @RequestBody(required = false) OilSetQueryVo oilSetQueryVo
//    ) {
//        // 建立PAGE對象 傳遞當前頁每頁筆數
//        Page<OilSet> page = new Page<>(current,limit);
//
//        // 組條件
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        Date para = null;
//        try {
//            String price_date = oilSetQueryVo.getPrice_date().toString();
//            String oil_92 = oilSetQueryVo.getOil_92().toString();
//            String oil_95 = oilSetQueryVo.getOil_95().toString();
//            String oil_98 = oilSetQueryVo.getOil_98().toString();
//            String diesel_fuel = oilSetQueryVo.getDiesel_fuel().toString();
//
//            Boolean hasWhere = false;
//            if (!StringUtils.isEmpty(price_date)) {
//                queryWrapper.like("price_date", price_date);
//                hasWhere = true;
//            }
//
//            if (!StringUtils.isEmpty(oil_92)) {
//                if (hasWhere) {
//                  queryWrapper.or();
//                }
//                queryWrapper.like("oil_92",oilSetQueryVo.getOil_92());
//                hasWhere = true;
//            }
//
//            if (!StringUtils.isEmpty(oil_95)) {
//                if (hasWhere) {
//                    queryWrapper.or();
//                }
//                queryWrapper.like("oil_95",oilSetQueryVo.getOil_95());
//                hasWhere = true;
//            }
//
//            if (!StringUtils.isEmpty(oil_98)) {
//                if (hasWhere) {
//                    queryWrapper.or();
//                }
//                queryWrapper.like("oil_98",oilSetQueryVo.getOil_98());
//                hasWhere = true;
//            }
//
//            if (!StringUtils.isEmpty(diesel_fuel)) {
//                if (hasWhere) {
//                    queryWrapper.or();
//                }
//                queryWrapper.like("diesel_fuel",oilSetQueryVo.getDiesel_fuel());
//                hasWhere = true;
//            }
//
//
//        }
//        catch (Exception e){
//            String errMsg = e.getMessage();
//            Result bad_req = new Result<>();
//            bad_req.code(202);
//            bad_req.message(ResultCodeEnum.PARAM_ERROR.getMessage() + errMsg);
//            return bad_req;
//        };
//
//        //調用方法實現功能
//        Page<OilSet> pageOilSet = oilSetService.page(page,queryWrapper);
//
//        return Result.ok(pageOilSet);
//    }

    // 新增
    @ApiOperation(value = "新增油價")
    @PostMapping("postOil")
    public Result postOilSet(@RequestBody OilSet oilSet) {

        boolean success = false;
        String errMsg = "";
        try {
            //新增的預設值
            oilSet.setIsDeleted(0);
            oilSet.setUpdateBy("");
            oilSet.setUpdateTime(null);
            success = oilSetService.save(oilSet);
        }
        catch(Exception e) {
            success = false;
            errMsg = e.getMessage();

        }

        if (success) {
            return Result.ok();
        }
        else {
            if (!StringUtils.isEmpty(errMsg)) {
                return Result.build(ResultCodeEnum.SAVE_ERROR.getCode()
                        ,ResultCodeEnum.SAVE_ERROR.getMessage()+":"+ errMsg);
            }
            else {
                return Result.fail();
            }
        }
    }

    // 修改
    @ApiOperation(value = "修改油價")
    @PutMapping("putOil")
    public Result putOilSet(@RequestBody OilSet oilSet) {

        boolean success = false;
        String errMsg = "";
        try {
            QueryWrapper updWapper = new QueryWrapper();
            updWapper.eq("price_date",oilSet.getPriceDate());
            success = oilSetService.update(oilSet,updWapper);
        }
        catch(Exception e) {
            success = false;
            errMsg = e.getMessage();

        }

        if (success) {
            return Result.ok();
        }
        else {
            if (!StringUtils.isEmpty(errMsg)) {
                return Result.build(ResultCodeEnum.UPDATE_ERROR.getCode()
                        ,ResultCodeEnum.UPDATE_ERROR.getMessage()+":"+ errMsg);
            }
            else {
                return Result.fail();
            }
        }
    }

    // 批次刪除
    @ApiOperation(value = "批次刪除油價資料")
    @DeleteMapping("deleteOilBatch")
    public Result delteOilSetBatch(@RequestBody List<String> para) {

        boolean sucess = false;
        String errMsg = "";
        QueryWrapper delWapper = new QueryWrapper();

        this.hasPara = false;
        try {
            para.forEach(item -> {
                if (!StringUtils.isEmpty(item)) {
                    if (hasPara) {
                        delWapper.or();
                    }
                    delWapper.eq("price_date", item);
                    hasPara = true;
                }
            });
            sucess = oilSetService.remove(delWapper);
        } catch (Exception e) {
            sucess = false;
            errMsg = e.getMessage();
        }

        if (sucess) {
            return Result.ok();
        }
        else {
            if (!StringUtils.isEmpty(errMsg)) {
                return Result.build(ResultCodeEnum.DELETE_ERROR.getCode()
                        ,ResultCodeEnum.DELETE_ERROR.getMessage()+":"+ errMsg);
            }
            else {
                return Result.fail();
            }
        }
    }


    // 刪除
    @ApiOperation(value = "刪除油價資料")
    @DeleteMapping("deleteOil/{price_date}")
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
