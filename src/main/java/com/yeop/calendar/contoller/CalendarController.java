package com.yeop.calendar.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {

    @RequestMapping("/")
    public String home(){

        return "index";
    }

    @GetMapping("/calendar")
    public String calendar(){

    }

}
