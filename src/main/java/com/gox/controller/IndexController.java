package com.gox.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.unionpay.acp.sdksample.back.Common;

@Controller
public class IndexController {
	
	
	
	@RequestMapping("/page/{index}")
	public String IndexPage(@PathVariable("index") String index){
		
		System.out.println(index);
		return index;
	}
	
	
}
