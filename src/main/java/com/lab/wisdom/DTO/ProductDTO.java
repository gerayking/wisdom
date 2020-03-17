package com.lab.wisdom.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String IfRain;
    private String Buzzer_OnOff;
    private String LED_OnOff;
    private String air;
    private String LED_Value;
    private String sunValue;
    private Float Hum;
    private Float Tem;
}
