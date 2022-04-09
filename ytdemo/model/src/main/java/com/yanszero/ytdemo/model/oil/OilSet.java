package com.yanszero.ytdemo.model.oil;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanszero.ytdemo.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Oil
 *
 * @author yanszero
 */
@Data
@ApiModel(description = "Oil")
@TableName("oil")
public class OilSet extends BaseEntity {

    // 記得變數命名時 底線要去除 不然mybatis 回傳會為NULL
    @ApiModelProperty(value = "公告日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("price_date")
    private Date priceDate;

    @ApiModelProperty(value = "92無鉛汽油")
    @TableField("oil_92")
    private BigDecimal oil92;

    @ApiModelProperty(value = "95無鉛汽油")
    @TableField("oil_95")
    private BigDecimal oil95;

    @ApiModelProperty(value = "98無鉛汽油")
    @TableField("oil_92")
    private BigDecimal oil98;

    @ApiModelProperty(value = "超級/高級柴油")
    @TableField("diesel_fuel")
    private BigDecimal dieselFuel;

    @ApiModelProperty(value = "建立者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "修改者")
    @TableField("update_by")
    private String updateBy;

}
