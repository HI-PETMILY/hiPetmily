package com.mypet.petmily.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    /* Main Default */
    @GetMapping(value = {"/", "/main"})
    public String defaultLocation() {
        return "main/main";
    }

    @PostMapping("/")
    public String redirectMain(){
        return "redirect:/";
    }








    /* footer 부분 page 이동 */
    @GetMapping("/board/terms")
    public String footerTerms() {
        return "board/terms";
    }

    @GetMapping("/board/policy")
    public String footerPolicy() {
        return "board/policy";
    }

    @GetMapping("/board/safe")
    public String footerSafe() {
        return "board/safe";
    }

    @GetMapping("/board/faq")
    public String footerFaq() {
        return "board/faq";
    }

}
