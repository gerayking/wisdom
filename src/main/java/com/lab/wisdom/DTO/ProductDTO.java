package com.lab.wisdom.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String time;
    private String IfRain;
    private String Buzzer_OnOff;
    private String LED_OnOff;
    private String air;
    private String gmtCreate;
    private Integer LED_Value;
    private Integer sunValue;
    private Float Hum;
    private Float Tem;
    private Float rainValue;
}
