package com.lab.wisdom.controller;

import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.Service.WisdomLampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class currentdateController {
    @Autowired
    WisdomLampService wisdomLampService;
    @Value("${Gizwits-App-did}")
    private  String did;
    @Value("${Gizwits-App-Id}")
    private String AppId;
    @GetMapping("/currentDate")
    public  String callback(Model model, HttpServletRequest request){
        request.getSession().setAttribute("section","currentDate");
        ProductDTO product = wisdomLampService.getProduct(AppId, did);
        model.addAttribute("product",product);
        return "currentData";
    }
}
