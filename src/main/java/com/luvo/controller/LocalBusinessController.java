package com.luvo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalBusinessController {

    @GetMapping("/business")
    public String showLocalBusinessPage() {
        return "local-business";
    }
}
