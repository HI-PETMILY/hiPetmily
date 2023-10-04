package com.mypet.petmily.member.controller;

import com.mypet.petmily.member.dto.MailDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.member.service.MailService;
import com.mypet.petmily.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor,
                            AuthenticationService authenticationService, PasswordEncoder passwordEncoder,
                            MailService mailService) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
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

    @GetMapping("/find_id_result")
    public void findIdResultPage(){}

    @PostMapping("/find_id-pwd")
    public String findId(@RequestParam("name") String memberName,
                         @RequestParam("phone") String phone,
                         Model model) {
        System.out.println("name: " + memberName + ", phone: " + phone);
        String result = memberService.findId(memberName, phone);

        if(result != null){
            model.addAttribute("result", result);   // 검색 결과를 Model에 추가
            model.addAttribute("showResult", true); // 결과를 보여주기 위한 플래그 추가
        }else {
            model.addAttribute("findIdError", "해당하는 사용자를 찾을 수 없습니다.");

        }
        return "/member/find_id_result";
    }




   @PostMapping("/find_id_result")
   // 이메일 전송
   public String sendEmail(@RequestParam("email") String email){
       MailDTO dto = mailService.createEmailContent(email);
       mailService.mailSned(dto);

       return "member/login";
   }



    @GetMapping("/pet-profile-regist")
    public void petProfileRegist(){}
}

