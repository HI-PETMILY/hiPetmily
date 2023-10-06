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
//        log.info("MemberList page : {}", page);
//        log.info("MemberList searchCondition : {}", searchCondition);
//        log.info("MemberList searchValue : {}", searchValue);
//
//        Map<String, String> searchMap = new HashMap<>();
//        searchMap.put("searchCondition", searchCondition);
//        searchMap.put("searchValue", searchValue);
//
//        Map<String, Object> memberListAndPaging = memberService1.selectMemberList(searchMap, page);
//        model.addAttribute("paging", memberListAndPaging.get("paging"));
//        model.addAttribute("memberList", memberListAndPaging.get("memberList"));

        return "admin/member/main-member";
    }

    @GetMapping("/secession")
    public String getSecessionPage(){

        return "admin/member/secession";
    }

    @GetMapping("/board")
    public String getBoardPage(){

        return "admin/member/board";
    }

    @GetMapping("/comment")
    public String getCommentPage(){

        return "admin/member/comment";
    }

    @GetMapping("/applyform")
    public String getApplyFormPage(){

        return "admin/member/applyform";
    }

    @GetMapping("/management")
    public String getApplicationPage(){

        return "admin/member/management";
    }

    @GetMapping("/reservation")
    public String getReservationPage(){

        return "admin/member/reservation";
    }

    @GetMapping("/automail")
    public String getAutoMailPage(){

        return "admin/MailManagement/automail";
    }

    @GetMapping("/atmosphere")
    public String getAtmospherePage(){

        return "admin/statistics/atmosphere";
    }

    @GetMapping("/complete")
    public String getCompletePage(){

        return "admin/statistics/complete";
    }

    @GetMapping("/sales")
    public String getSalesPage(){

        return "admin/statistics/sales";
    }

}
