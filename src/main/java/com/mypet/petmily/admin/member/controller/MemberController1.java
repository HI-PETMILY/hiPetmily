package com.mypet.petmily.admin.member.controller;

import com.mypet.petmily.admin.member.service.MemberService1;
import com.mypet.petmily.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class MemberController1 {
    private final MemberService1 memberService1;

    public MemberController1(MemberService1 memberService1) {this.memberService1 = memberService1;}

    @GetMapping("/main-member")
    public String getMemberList(Model model){

         List<MemberDTO> memberList = memberService1.selectMemberList();
         log.info("memberList : {} ", memberList);

         model.addAttribute("memberList", memberList);

//        System.out.println(memberService1.selectMemberList());

        return "admin/member/main-member";
    }



}
