package com.yanszero.ytdemo.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseMongoEntity implements Serializable {

//    @ApiModelProperty(value = "id")
//    @Id
//    private String id;

    @ApiModelProperty(value = "建立時間")
    private Date createTime;

    @ApiModelProperty(value = "修改時間")
    private Date updateTime;

    @ApiModelProperty(value = "邏輯删除(1:已删除，0:未删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他參數")
    @Transient //被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性
    private Map<String,Object> param = new HashMap<>();
}
