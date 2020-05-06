package com.wuzz.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuzz.rest.processor.RestfulApiProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/23
 * Time: 12:27
 * Description 描述:
 */
@Controller
@RequestMapping("/api")
public class RestfulApiController {

//    @Value("${restful.api.path:/}")
//    private String restfulApipATH = "";

    @Autowired
    private RestfulApiProcessor RestfulApiProcessor;


    @RequestMapping(method = RequestMethod.GET)
    public String status(Model model) {
        model.addAttribute("result",RestfulApiProcessor.restfulApiList);
        model.addAttribute("applicationName","电竞APP");
        return "api";
    }
}
