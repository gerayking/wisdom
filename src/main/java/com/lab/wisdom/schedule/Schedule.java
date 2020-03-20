package com.lab.wisdom.schedule;

import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.Service.WisdomLampService;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
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
    @Scheduled(cron = "0 0 8 * * ?")
    public void recordMorning(){ restoreDate("早"); }
    @Scheduled(cron = "0 0 12 * * ?")
    public void recordAfter(){ restoreDate("中"); }
    @Scheduled(cron = "0 0 18 * * ?")
    public void recordMid(){
        restoreDate("晚");
    }
    private void restoreDate(String s) {
        ProductDTO product = wisdomLampService.getProduct(AppId, did);
        wisdomLamp record = new wisdomLamp();
        BeanUtils.copyProperties(product,record);
        SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        String data = format.format(new Date(System.currentTimeMillis()));
        System.out.println(data);
        record.setGmtCreate(data+s);
        wisdomLampMapper.insert(record);
    }
}
