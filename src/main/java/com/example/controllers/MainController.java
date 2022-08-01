package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	

    @RequestMapping("/")
    public String index() {
        return "login";
    }
    
    @RequestMapping("login")
    public String login() {
    	return "calendar";
    }
    @RequestMapping("index")
    public String homeIndex() {
        return "index";
    }
}
    
