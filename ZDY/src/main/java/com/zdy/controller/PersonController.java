package com.zdy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdy.entity.Person;
import com.zdy.entity.User;
import com.zdy.service.PersonManager;
import com.zdy.utils.Page;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Resource(name = "personManager")
	private PersonManager personManager;
private final Integer pageSize = 10;
	
	@RequestMapping("/getAllPerson")
	public String getAllPerson(Integer page,String xm,String sfzh,HttpServletRequest request){
		if(page==null)page=1;
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("xm", xm);
		conditions.put("sfzh", sfzh);
		Page<Person> pages = personManager.findPagePersonByIntegrateds((--page)*pageSize, pageSize,conditions);
		pages.setTotalPage((int)Math.ceil((float)pages.getTotal()/(float)pageSize));
		request.setAttribute("pages", pages);
		request.setAttribute("page", ++page);
		request.setAttribute("xm", xm);
		request.setAttribute("sfzh", sfzh);
		return "person/personManager";
	}

	
	@RequestMapping("/delPerson")
	public void delPerson(String ids, HttpServletResponse response) {
		String message = "删除失败！";
		if(ids!=null){
			Integer upResult = personManager.updatePersons(ids);
			if(upResult>0){
				message = "删除成功！";
			}
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
	

	@RequestMapping("/toAddPerson")
	public String toAddPerson(HttpServletRequest request) {
		return "person/toaddperson";
	}

	@RequestMapping("/addPerson")
	public void addPerson(Person person,HttpServletResponse response,HttpSession httpSession) {
		String message = "添加成功！";
		if(person.getId()!=null){
			message = "修改成功！";
		}
		User user = (User) httpSession.getAttribute("user");
		person.setJybm(user.getBm());
		person.setJysfzh(user.getUsername());
		person.setJysj(user.getSj());
		person.setJyxm(user.getXm());
		person.setLrsj(new Date());
		personManager.savePerson(person);
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


	@RequestMapping("/toUpdatePerson")
	public String updatePerson(Long id, HttpServletRequest request) {
		if(id!=null){
			request.setAttribute("zperson", personManager.findPersonById(id));
		}
		return "person/toaddperson";
	}
	
}
