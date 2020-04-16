package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

	@RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String remove(
				@PathVariable("adminId") Integer adminId,
				@PathVariable("pageNum") Integer pageNum,
				@PathVariable("keyword") String keyword
			) {
		
		// 执行删除
		adminService.remove(adminId);
		
		// 页面跳转：回到分页页面
		
		// 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
		// return "admin-page";
		
		// 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能
		// return "forward:/admin/get/page.html";
		
		// 尝试方案3：重定向到/admin/get/page.html地址
		// 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}

    @RequestMapping("/admin/do/logout.html")
    public String doLoginout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }


    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd")  String userPswd,
            HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
//        return "admin-main";
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                              ModelMap modelMap
                              ){
        PageInfo <Admin> pageInfo = adminService.getPageInfo(keyword,pageNum,pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";

    }


}
