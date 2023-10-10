package com.mypet.petmily.main.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    /* Main Default */
    @GetMapping(value = {"/", "/main"})
    public String defaultLocation() {
        return "/main/mainPage";
    }

    @PostMapping("/")
    public String redirectMain(){
        return "redirect:/";
    }


    /* Header 부분 page 이동 */
    // 공지사항
    @GetMapping("/board/notice")
    public String headerNotice() {
        return "board/notice";
    }

    // 펫시터 찾기
    @GetMapping("/petSitter/search")
    public String headerPetsitterSearch() {
        return "petSitter/search";
    }

    // 펫시터 지원
    @GetMapping("/petSitter/join")
    public String headerPetsitterJoin() {
        return "petSitter/join";
    }

    // 고객센터
    @GetMapping("/board/info")
    public String headerInfo() {
        return "board/info";
    }

    // 회원가입
    @GetMapping("/member/join")
    public String headerMemberJoin() {
        return "member/join";
    }

    // 로그인
//    @GetMapping("/member/login")
//    public String headerMemberLogin() {
//        return "member/login";
//    }

    // 마이페이지
    @GetMapping("/member/mypage")
    public String headerMemberMypage(@AuthenticationPrincipal MemberDTO member) {
        return "member/mypage";
    }

    // 로그아웃
    @GetMapping("/member/logout")
    public String headerMemberLogout() {
        return "member/logout";
    }

    // 관리자
    @GetMapping("/admin/main")
    public String headerAdminMain() {
        return "admin/main";
    }


    /* footer 부분 page 이동 */
    // 이용약관
    @GetMapping("/board/terms")
    public String footerTerms() {
        return "board/terms";
    }

    // 개인정보처리방침
    @GetMapping("/board/policy")
    public String footerPolicy() {
        return "board/policy";
    }

    // 안전보상프로그램
    @GetMapping("/board/safe")
    public String footerSafe() {
        return "board/safe";
    }

    // 자주하는질문
    @GetMapping("/board/faq")
    public String footerFaq() {
        return "board/faq";
    }

}
