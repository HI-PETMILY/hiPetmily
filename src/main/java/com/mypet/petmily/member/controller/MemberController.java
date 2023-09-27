package com.mypet.petmily.member.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.service.MemberService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

//    private final MemberService memberService;
//
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    /* 회원 가입 메인 페이지 이동 */
    @GetMapping("/mainRegist")
    public void mainRegistPage(){}

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage(){}

    @GetMapping("/completedRegist")
    public void completedRegistPage(){}



    /* 닉네임 중복 체크 */
//    @PostMapping("/nickNameDupCheck")
//    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member) {
//
//        log.info("Request Check NickName : {}", member.getNickName());
//
//        String result = "사용 가능한 닉네임입니다.";
//
//        if(memberService.selectMemberByNickName(member.getNickName())) {
//            result = "중복 된 닉네임이 존재합니다.";
//        }
//
//        return ResponseEntity.ok(result);
//
//    }
}
