package com.yanszero.ytdemo.vo.oil;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OilSetQueryVo {

    // 建立VO類封裝條件值
    @ApiModelProperty(value = "公告日期")
    private String price_date;

    @ApiModelProperty(value = "92無鉛汽油")
    private BigDecimal oil_92;

    @ApiModelProperty(value = "95無鉛汽油")
    private BigDecimal oil_95;

    @ApiModelProperty(value = "98無鉛汽油")
    private BigDecimal oil_98;

    @ApiModelProperty(value = "超級/高級柴油")
    private BigDecimal diesel_fuel;
}
