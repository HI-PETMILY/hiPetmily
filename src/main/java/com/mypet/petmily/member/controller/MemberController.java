package com.mypet.petmily.member.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor,
                            AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public void loginPage(){}

    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr){
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
    }

    @GetMapping("/regist")
    public void registPage(){}


    @GetMapping("/find_id-pwd")
    public void findIdPwdPage(){}





    @GetMapping("/pet-profile-regist")
    public void petProfileRegist(){}
}

