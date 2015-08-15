package com.zdy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdy.entity.Menu;
import com.zdy.entity.Resources;
import com.zdy.entity.User;
import com.zdy.service.MenuManager;
import com.zdy.service.ResourceManager;
import com.zdy.service.UserManager;
import com.zdy.utils.MD5Util;
import com.zdy.utils.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    @Qualifier("userManager")
	private UserManager userManager;
	@Autowired
    @Qualifier("resourceManager")
	private ResourceManager resourceManager;
	
	@Autowired
    @Qualifier("menuManager")
	private MenuManager menuManager;
	
	
	private final Integer pageSize = 10;

	@RequestMapping("/getUserPageAll")
	public String getAllUser(Integer page,String userName,HttpServletRequest request) {
		if(page==null)page=1;
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("username", userName);
		Page<User> pages = userManager.findPageUserByIntegrateds((--page)*pageSize, pageSize,conditions);
		pages.setTotalPage((int)Math.ceil((float)pages.getTotal()/(float)pageSize));
		request.setAttribute("pages", pages);
		request.setAttribute("page", ++page);
		request.setAttribute("username", userName);
		return "user/userManager";
	}

	@RequestMapping("/getUser")
	public String getUser(User user, HttpServletRequest request,HttpSession httpSession) {
		if(user.getMm()!=null){
			user.setMm(MD5Util.string2MD5(user.getMm()));
		}
		User userC = userManager.getUser(user);
		List<Resources> caselist;
		List<Menu> mainlist;
		if(userC==null){
			request.setAttribute("error","用户名或密码错误");
			return "../../Login";
		}else{
			caselist = new ArrayList<Resources>();
			caselist = resourceManager.getResourceAll(userC.getId());
			if(caselist==null||caselist.size()==0){
				request.setAttribute("error","您的帐号没有相关权限，请联系负责人！");
				return "../../Login";
			}
			mainlist = new ArrayList<Menu>();
			mainlist = menuManager.getMenuAll(userC.getId());
			httpSession.setAttribute("mainlist", mainlist);//主菜单
			httpSession.setAttribute("caselist", caselist);//主菜单
			
			httpSession.setAttribute("user", userC);
			request.setAttribute("user", userManager.getUser(user));
			return "main/main";
		}
	}
	@RequestMapping("/delUser")
	public void delUser(String ids, HttpServletResponse response) {
		Integer upResult = userManager.updateUsers(ids);
		String message = "删除失败！";
		if(upResult>0){
			message = "删除成功！";
		}
		response.setContentType("text/plain; charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(message);
			out.flush();   
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request) {
		return "user/toadduser";
	}

	@RequestMapping("/checkUserName")
	public void addUser(String username, HttpServletResponse response) {
		Integer result = userManager.checkUserName(username);
        response.setContentType("text/plain;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(result.toString());
			out.flush();   
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/addUser")
	public void addUser(User user,HttpServletResponse response) {
		String message = "修改成功！";
		if(user.getId()==null){
			user.setMm(MD5Util.string2MD5(user.getMm()));
			message = "添加成功！";
		}
		userManager.saveUser(user);
		 response.setContentType("text/plain;charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
				out.write(message);
				out.flush();   
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping("/toChangeUser")
	public String toChangeUser(HttpServletRequest request) {
		return "user/toChangeUser";
	}
	@RequestMapping("/changeUser")
	public void changeUser(String password,String passwords,HttpServletRequest request,HttpServletResponse response) {
		String message = "0";
		password = MD5Util.string2MD5(password);
		User user = (User) request.getSession().getAttribute("user");
		if(password.equals(user.getMm())){
			user.setMm(MD5Util.string2MD5(passwords));
			userManager.saveUser(user);
		}else{
			message = "1";
		}
	    response.setContentType("text/plain;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(message);
			out.flush();   
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/toUpdateUser")
	public String updateUser(Long id, HttpServletRequest request) {
		request.setAttribute("zuser", userManager.findUserById(id));
		return "user/toadduser";
	}
	@RequestMapping("/logoutUser")
	public String logoutUser(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("mainlist");
		return "../../Login";
	}
}
