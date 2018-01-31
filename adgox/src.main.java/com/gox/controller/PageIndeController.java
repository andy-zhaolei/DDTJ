package com.gox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageIndeController {
	@RequestMapping("/page/{index}")
	public String Index(@PathVariable("index") String index){
		return index;		
	}
}
