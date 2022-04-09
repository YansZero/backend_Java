package com.yanszero.ytdemo.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
//核心類
@Repository
public class BaseEntity implements Serializable {

//    @ApiModelProperty(value = "id")
//    @TableId(type = IdType.AUTO)
//    private Long id;

    @ApiModelProperty(value = "建立時間")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date create_Time;

    @ApiModelProperty(value = "修改時間")
    @TableField("update_time")
    private Date update_Time;

    @ApiModelProperty(value = "邏輯删除(1:已删除，0:未删除)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他參數")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
