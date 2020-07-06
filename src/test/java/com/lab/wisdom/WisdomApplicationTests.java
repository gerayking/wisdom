package com.lab.wisdom;

import com.alibaba.fastjson.JSON;
import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.DTO.jsonAccepptDTO;
import com.lab.wisdom.Service.WisdomLampService;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@SpringBootTest
class WisdomApplicationTests {
    @Autowired
    private WisdomLampService wisdomLampService;
    @Autowired
    private WisdomLampMapper wisdomLampMapper;
    @Value("${Gizwits-App-Id}")
    private  String AppId;
    @Value("${Gizwits-App-did}")
    private  String did;
    @Test
    public void getProduct(){
        ProductDTO product = wisdomLampService.getProduct(AppId, did);
        /*
         * 判断设备是否在线，不在线则不插入数据库，否则影像数据可读性*/
        if(product.getAir() == null ||
                product.getHum() == null ||
                product.getBuzzer_OnOff() == null ||
                product.getIfRain() == null ||
                product.getLED_OnOff() == null ||
                product.getLED_Value() == null ||
                product.getSunValue() == null) {
            return ;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:00");
        String data = format.format(new Date(System.currentTimeMillis()));
        wisdomLamp record = new wisdomLamp();
        record.setAir(product.getAir());
        record.setBuzzerOnoff(product.getBuzzer_OnOff());
        record.setHum(product.getHum());
        record.setIfrain(product.getIfRain());
        record.setTem(product.getTem());
        record.setSunvalue(product.getSunValue());;
        record.setLedOnoff(product.getLED_OnOff());
        record.setLedValue(product.getLED_Value());
        record.setGmtCreate(data);
        record.setRainValue(product.getRainValue());
        wisdomLampMapper.insert(record);
    }

}
