package com.lab.wisdom.controller;

import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.Service.WisdomLampService;
import com.lab.wisdom.mapper.WisdomLampExtMapper;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "获取数据")
@Controller
public class DataAccessController {
    @Autowired
    private WisdomLampService wisdomLampService;
    @Autowired
    private WisdomLampExtMapper wisdomLampExtMapper;
    @Autowired
    private WisdomLampMapper wisdomLampMapper;
    @Value("${Gizwits-App-did}")
    private  String did;
    @Value("${Gizwits-App-Id}")
    private String AppId;
    @ApiOperation("获取某种类型数据Tem/Hum/LED_Value/sunValue/rainValue")
    @RequestMapping(value = "/getdate",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Float> getDate(@ApiParam("数据类型")
                                                 String dateType,
                                     @ApiParam("数据条目数")
                                     @RequestParam(defaultValue = "10")
                                             int num ){
        Map<String,Float> res = new HashMap<>();
        List<ProductDTO> wisdomLampList = wisdomLampService.list(num);
        switch (dateType){
            case "rainValue":
                for (ProductDTO wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(), wisdomLamp.getRainValue());
                }
                break;
            case "Tem":
                for (ProductDTO wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(), wisdomLamp.getTem());
                }
                break ;
            case  "Hum":
                for (ProductDTO wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(),wisdomLamp.getHum());
                }
                break;
            case "LED_Value":
                for (ProductDTO wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(),(float)wisdomLamp.getLED_Value());
                }
                break;
            case "sunValue" :
                for (ProductDTO wisdomLamp : wisdomLampList) {
                    res.put(wisdomLamp.getGmtCreate(), (float) (wisdomLamp.getSunValue()));
                }
        }
        return res;
    }
    @ApiOperation("获取当前数据")
    @RequestMapping(value = "/getCurrentDate",method = {RequestMethod.POST})
    @ResponseBody
    public ProductDTO getCurrentDate(){
        Map<String,Float> res = new LinkedHashMap<>();
        ProductDTO wisdomLamp = wisdomLampService.getProduct(AppId, did);
        return wisdomLamp;
    }
}
