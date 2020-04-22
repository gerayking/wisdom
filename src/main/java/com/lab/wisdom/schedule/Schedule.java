package com.lab.wisdom.schedule;

import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.Service.WisdomLampService;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class Schedule {
    @Autowired
    private WisdomLampService wisdomLampService;
    @Autowired
    private WisdomLampMapper wisdomLampMapper;
    @Value("${Gizwits-App-Id}")
    private String AppId;
    @Value("${Gizwits-App-did}")
    private  String did;
    @Scheduled(cron = "0 0 6 * * ?")
    public void recordAt6(){ restoreDate(); }
    @Scheduled(cron = "0 0 9 * * ?")
    public void recordAt9(){ restoreDate(); }
    @Scheduled(cron = "0 0 12 * * ?")
    public void recordAt12(){restoreDate(); }
    @Scheduled(cron = "0 0 15 * * ?")
    public void recordAt15(){ restoreDate(); }
    @Scheduled(cron = "0 0 18 * * ?")
    public void recordAt18(){ restoreDate(); }
    @Scheduled(cron = "0 0 21 * * ?")
    public void recordAt24(){ restoreDate(); }
    @Scheduled(cron = "0 0 0 * * ?")
    public void recordAt0(){ restoreDate(); }
    private void restoreDate() {
        ProductDTO product = wisdomLampService.getProduct(AppId, did);
        /*
        * 判断设备是否在线，不在线则不插入数据库，否则影像数据可读性*/
        if(product.getAir() == null ||
           product.getHum() == null ||
           product.getBuzzer_OnOff() == null ||
           product.getIfRain() == null ||
           product.getLED_OnOff() == null ||
           product.getLED_Value() == null ||
           product.getSunValue() == null ||
           product.getTime() == null) {
            return ;
        }
        wisdomLamp record = new wisdomLamp();
        BeanUtils.copyProperties(product,record);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:00");
        String data = format.format(new Date(System.currentTimeMillis()));
        record.setGmtCreate(data);
        wisdomLampMapper.insert(record);
    }
}
