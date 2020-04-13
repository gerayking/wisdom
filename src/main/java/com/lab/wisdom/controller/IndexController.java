package com.lab.wisdom.controller;

import com.lab.wisdom.DTO.ProductDTO;
import com.lab.wisdom.Service.WisdomLampService;
import com.lab.wisdom.mapper.WisdomLampMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private WisdomLampMapper wisdomLampMapper;
    @Autowired
    private WisdomLampService LampService;
    @Value("${Gizwits-Username}")
    private String username;
    @Value("${Gizwits-password}")
    private String password;
    @Value("${Gizwits-Product-Key}")
    private String productId;
    @Value("${Gizwits-Product-Secret}")
    private String productSecret;
    @Value("${Gizwits-App-Id}")
    private String AppId;
    @Value("${Gizwits-App-Secret}")
    private String AppSecret;
    @Value("${Gizwits-App-did}")
    private  String did;
    @GetMapping("/")
    public  String callback(Model model, HttpServletRequest request){
        List<ProductDTO> productDTOS = LampService.list();
        request.getSession().setAttribute("section","index");
        model.addAttribute("productDTOS",productDTOS);
        return "index";
    }
}
