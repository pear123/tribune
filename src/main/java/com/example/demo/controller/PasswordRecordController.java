package com.example.demo.controller;

import com.example.demo.entity.PasswordRecord;
import com.example.demo.entity.User;
import com.example.demo.service.PasswordRecordService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/myPassword")
public class PasswordRecordController {
    @Resource
    private PasswordRecordService password_recordService;
    @Resource
    private UserService userService;

    /**
    * @Description:  跳转到获取密码的页面
    * @Param: []
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("preFindPassword")
    public String  preFindPassword()throws Exception {
        return "findPassword";
    }

    /**
    * @Description: 返回验证码
    * @Param: [model, phone]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping(value="/validateNumber_",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody
    String validate_number(Model model, String phone) throws Exception {
        User user=userService.queryUserByPhone(phone);
        Date date=new Date();
        if(user!=null){
            PasswordRecord passwordRecord=new PasswordRecord();
            String code=userService.getCode();
            Integer number=Integer.parseInt(code);
            passwordRecord.setuPhone(phone);
            passwordRecord.setValidateNumber(number);
            passwordRecord.setpCreatetime(date);
            passwordRecord.setStatus("0");//未失效
           boolean b= password_recordService.addPasswordRecord(passwordRecord);
           if(b){
               return "identifying code is："+code;
           }
        }
        return "Acquisition failure";
    }


    /**
    * @Description: 密码找回时核对验证码
    * @Param: [model, phone, validate_number]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping(value="/findPassword",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody
    String findPassword(Model model, String phone, String validateNumber) throws Exception {
        Date date=new Date();
        Integer validate_number2=Integer.parseInt(validateNumber);
        String password=password_recordService.getPassword(phone,validate_number2,date);
        if(password.equals("")){
            return "fail";
        }

       return "success";
    }

}
