package com.lab.wisdom.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.ExcelDTO.PersonExportVo;
import com.lab.wisdom.ExcelDTO.Smartlamp;
import com.lab.wisdom.mapper.WisdomLampMapper;
import com.lab.wisdom.model.wisdomLamp;
import com.lab.wisdom.model.wisdomLampExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags="导出全部数据为Excel")
@Controller
public class searchdataController {
    @Autowired
    WisdomLampMapper wisdomLampMapper;
    @ApiOperation("导出为excel")
    @RequestMapping(value = "/ExportExcel",method = {RequestMethod.GET})
    @ResponseBody
    public void ExportExcel(@ApiParam("权限id(暂无验证)") String appid, @ApiParam("密码(暂无验证)") String pass
            , HttpServletResponse response){
        List<wisdomLamp> wisdomLampList = wisdomLampMapper.selectByExample(new wisdomLampExample());
        List<Smartlamp> smartlampList = new ArrayList<>();
        for (wisdomLamp lamp : wisdomLampList) {
            Smartlamp smartlamp = new Smartlamp();
            smartlamp.setLED_Value(lamp.getLedValue());
            smartlamp.setTem(lamp.getTem());
            smartlamp.setHum(lamp.getHum());
            smartlamp.setSunValue(lamp.getSunvalue());
            smartlamp.setLED_OnOff(lamp.getLedOnoff());
            smartlamp.setAlarm_OnOff(lamp.getBuzzerOnoff());
            smartlamp.setAir(lamp.getAir());
            smartlamp.setDate(lamp.getGmtCreate());
            smartlampList.add(smartlamp);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("智慧灯杆", "智慧灯杆"), Smartlamp.class, smartlampList);
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
        response.setCharacterEncoding("UTF-8");
        try {
            workbook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}