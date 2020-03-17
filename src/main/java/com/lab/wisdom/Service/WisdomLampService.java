package com.lab.wisdom.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lab.wisdom.DTO.AccesstokenDTO;
import com.lab.wisdom.DTO.ProductDTO;
import okhttp3.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lab.wisdom.DTO.jsonAccepptDTO;

import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.util.List;

@Service
public class WisdomLampService {
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
    public ProductDTO getProduct(String appId,String did){
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
            System.out.println(jsonAccepptDTO.getAttr());
            return productDTO;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
