package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
;

@Controller
public class MainController {
	
	

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("login")
    public String login() {
    	System.out.println("login");
    	return "login";
    }
    
    
    
    
    
    
   
}
    
