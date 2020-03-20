package com.lab.wisdom.controller;

import com.lab.wisdom.mapper.WisdomLampExtMapper;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataAccessController {
    @Autowired
    private WisdomLampExtMapper wisdomLampExtMapper;
    @Autowired
    private WisdomLampMapper wisdomLampMapper;
    @RequestMapping(value = "/getdate",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Float> getDate(HttpServletRequest request, HttpSession session){
        String dataType = request.getParameter("dateType");
        Map<String,Float> res = new LinkedHashMap<>();
        List<wisdomLamp> wisdomLampList = wisdomLampExtMapper.limit(10);
        switch (dataType){
            case "Tem":
                for (wisdomLamp wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(), wisdomLamp.getTem());
                }
                break ;
            case  "Hum":
                for (wisdomLamp wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(),wisdomLamp.getHum());
                }
                break;
            case "LED_Value":
                for (wisdomLamp wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(),new Float(wisdomLamp.getLedValue()));
                }
                break;
            case "sunValue" :
                for (wisdomLamp wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(),new Float(wisdomLamp.getSunvalue()));
                }
        }
        System.out.println(res.toString());
        return res;
    }
}
