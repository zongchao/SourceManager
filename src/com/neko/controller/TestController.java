package com.neko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController
{
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloWord(Model model) {
        String message = "Hello World, Spring 3.0!";
        model.addAttribute("message", message);
        return "/login/hello";
    }
}
