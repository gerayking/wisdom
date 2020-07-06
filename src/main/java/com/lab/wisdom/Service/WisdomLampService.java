package com.lab.wisdom.Service;

import com.lab.wisdom.mapper.WisdomLampExtMapper;
import com.alibaba.fastjson.JSON;
import com.lab.wisdom.DTO.AccesstokenDTO;
import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.DTO.jsonAccepptDTO;
import com.lab.wisdom.model.wisdomLamp;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WisdomLampService {
    @Autowired
    private WisdomLampExtMapper wisdomLampExtMapper;

    //这个没用到，是获取token的
    public String getToken(AccesstokenDTO accesstokenDTO){
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
        Request request = new Request.Builder()
                .url("http://api.gizwits.com/app/login")
                .post(body)
                .header("X-Gizwits-Application-Id",accesstokenDTO.getAppId())
                .build();
        try (Response response = client.newCall(request).execute()){
            String string = response.body().string();
            String[] split = string.split("&");
            String Token = split[0].split("=")[1];
            return Token;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //获取当前数据并进行解析
    public ProductDTO getProduct(String appId,String did){
        System.out.println("hear");
        Request request = new Request.Builder()
                .url("https://api.gizwits.com/app/devdata/"+did+"/latest")
                .header("X-Gizwits-Application-Id",appId)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        try(Response response = client.newCall(request).execute()){
            String jsonstring = response.body().string();
            jsonAccepptDTO jsonAccepptDTO = JSON.parseObject(jsonstring, jsonAccepptDTO.class);
            ProductDTO productDTO = JSON.parseObject(jsonAccepptDTO.getAttr(), ProductDTO.class);
            return productDTO;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //获查询数据库中的数据
    public List<ProductDTO> list(int num) {
        List<ProductDTO>productDTOS = new ArrayList<>();
        List<wisdomLamp> limit = wisdomLampExtMapper.limit(num);
        for (wisdomLamp wisdomLamp : limit) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setTime(wisdomLamp.getGmtCreate());
            productDTO.setAir(wisdomLamp.getAir());
            productDTO.setBuzzer_OnOff(wisdomLamp.getBuzzerOnoff());
            productDTO.setHum(wisdomLamp.getHum());
            productDTO.setIfRain(wisdomLamp.getIfrain());
            productDTO.setLED_Value(wisdomLamp.getLedValue());
            productDTO.setLED_OnOff(wisdomLamp.getLedOnoff());
            productDTO.setTem(wisdomLamp.getTem());
            productDTO.setGmtCreate(wisdomLamp.getGmtCreate());
            productDTO.setSunValue(wisdomLamp.getSunvalue());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
