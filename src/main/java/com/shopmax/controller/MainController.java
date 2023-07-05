package com.shopmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value="/")	//localhost로 접속하면 main페이지로 접속
	public String main(){
		return "main";
	}
 }
