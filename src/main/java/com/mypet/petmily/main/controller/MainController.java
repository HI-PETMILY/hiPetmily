package com.mypet.petmily.main.controller;

import com.mypet.petmily.main.service.MainService;
import com.mypet.petmily.petSitterNew.dto.SitterReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    /* Main Default */
    @GetMapping(value = {"/", "/main"})
    public String defaultLocation(Model model) {

        // mainpage review
        List<SitterReviewDTO> reviewList = mainService.selectAllReviews();

        log.info("-- reviewList : {}", reviewList);

        model.addAttribute("reviewList", reviewList );

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
    public String headerPetsitterSearch( Model model) {

       return "petSitter/searchPage";
    }

    // 펫시터 지원
    @GetMapping("/petSitterNew/regist")
    public String headerPetsitterJoin() {
        return "petSitterNew/petSitterRegist";
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



}
