package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {
    Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Autowired
    private AdminService adminService;

    @RequestMapping("/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){
        boolean requestType = CrowdUtil.judgeRequestType(request);
        logger.info("请求类型 : " + requestType);

        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);
//        System.out.println(10/0);
        String a = null;
        System.out.println(a.length());

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
    public String testReceiveComposeObject(@RequestBody Student student,HttpServletRequest request) {


        boolean requestType = CrowdUtil.judgeRequestType(request);
        logger.info("请求类型 : " + requestType);
        logger.info(student.toString());


        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/compose/objectResultEntity.json")
    public ResultEntity<Student> objectResultEntity(@RequestBody Student student) {

        logger.info(student.toString());
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);


        //测试异常用
//        String a = null;
//        System.out.println(a.length());
        return resultEntity;
    }
}
