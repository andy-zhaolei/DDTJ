package com.gox.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gox.manage.model.User;
import com.gox.manage.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/test.json")
	public void  find(HttpServletResponse response) throws IOException {
			
			List<User> list=userService.findAll();
			System.out.println("UserList "+list.size());
			
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONArray json=JSONArray.fromObject(list,jsonConfig);
			response.getWriter().write(json.toString());
	
	}
	@RequestMapping("/DeleteUser/{id}")
	public String deleteUser(@PathVariable   Long id){
		userService.deleteUser(id);
		
		return "redirect:/page/user_list.html";
	}

}
