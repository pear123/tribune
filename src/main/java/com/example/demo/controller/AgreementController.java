package com.example.demo.controller;

import com.example.demo.entity.Agreement;
import com.example.demo.service.AgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/myAgreement")
public class AgreementController {
    @Resource
    private AgreementService agreementService;

    /**
    * @Description:  查看注册协议
    * @Param: [model]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("queryAgreement")
    public String  queryAgreement(Model model)throws Exception {
        Agreement agreement=agreementService.queryAgreementByName("注册协议");
        model.addAttribute("agreement",agreement);
        return "queryAgreement";
    }
}
