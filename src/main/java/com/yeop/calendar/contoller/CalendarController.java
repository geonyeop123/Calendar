package com.yeop.calendar.contoller;

import com.yeop.calendar.domain.CalendarMaker;
import com.yeop.calendar.domain.CalendarVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CalendarController {

    @RequestMapping("/calendar")
    public String home(CalendarVO vo, Model m){
        /////
        // 선언
        /////
        LocalDate date = LocalDate.now();

        /////
        // 유효성 검사
        /////
        if(vo.getYear() == null || vo.getMonth() == null){
            vo = new CalendarVO(date.getYear(), date.getMonth().getValue());
        }

        /////
        // 반환
        /////
        m.addAttribute(vo);
        return "calendar";
    }

    @ResponseBody
    @PostMapping("/proc")
    public CalendarVO proc(CalendarVO vo){

        CalendarMaker cm = new CalendarMaker(vo);
        vo.setCm(cm);

        return vo;
    }

}
