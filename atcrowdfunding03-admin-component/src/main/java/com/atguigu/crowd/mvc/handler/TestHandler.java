package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestHandler {
    Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Autowired
    private AdminService adminService;

    @RequestMapping("/ssm.html")
    public String testSsm(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);

        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiverArrayThree(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(TestHandler.class);
        for (Integer number : array){
            logger.info("number :   -- >" + number);
        }
        Student student = new Student();
        student.setStuName("testName");
        System.out.println("测试lambda表达式的值" + student.getStuName());
        return "success";

    }


    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public String testReceiveComposeObject(@RequestBody Student student) {


        logger.info(student.toString());


        return "success";
    }
}
