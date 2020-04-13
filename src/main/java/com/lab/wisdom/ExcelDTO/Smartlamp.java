package com.lab.wisdom.ExcelDTO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class Smartlamp implements  Serializable {
    private static final long serialVersionUID = 1L;
    @Excel(name="日期",orderNum = "0",width = 20)
    private String date;
    @Excel(name = "路灯开关",orderNum = "2",width = 15)
    private String LED_OnOff;
    @Excel(name = "空气质量",orderNum = "3",width = 15)
    private String air;
    @Excel(name = "路灯亮度",orderNum = "4",width = 15)
    private Integer LED_Value;
    @Excel(name = "光线强度（%）",orderNum = "5",width = 15)
    private Integer sunValue;
    @Excel(name = "报警器开关",orderNum = "6",width = 15)
    private String alarm_OnOff;
    @Excel(name = "湿度（%）",orderNum = "7",width = 15)
    private Float hum;
    @Excel(name = "温度（°C）",orderNum = "8",width = 15)
    private Float tem;
}
