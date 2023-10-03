package com.mypet.petmily.member.controller;

import com.mypet.petmily.common.exception.member.MemberModifyException;
import com.mypet.petmily.common.exception.member.MemberRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
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

    public MemberController(MemberService memberService,
                            AuthenticationService authenticationService,
                            MessageSourceAccessor messageSourceAccessor,
                            PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.passwordEncoder = passwordEncoder;
    }

    /* 로그인 페이지 ~ */


    /* 회원 가입 메인 페이지 이동 */
    @GetMapping("/mainRegist")
    public void mainRegistPage(){}

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage(){}

    /* 회원 가입 완료 페이지 */
    @GetMapping("/completedRegist")
    public void comRegistPage(){}

    /* 마이페이지 */
    @GetMapping("/mypage")
    public void myPage(){}

    /* 내 정보 확인 */
    @GetMapping("/myInfo")
    public void modifyPage(){} /* 로그인 시 멤버 정보 불러오기 짜야함 */
//    @GetMapping("/update")
//    public void modifyPage(@AuthenticationPrincipal MemberDTO member, Model model) {
//
//        String[] address = member.getAddress().split("\\$");
//        model.addAttribute("address", address);
//    }

    /* 회원 정보 수정 */
    @PostMapping("/myInfo")
    public String modifyMember(MemberDTO modifyMember, String zipCode, String address1, String address2,
                               @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr) throws MemberModifyException {
        String address = zipCode + "$" + address1 + "$" + address2;
        modifyMember.setAddress(address);
        modifyMember.setMemberNo(loginMember.getMemberNo());

        log.info("modifyMember request Member : {}", modifyMember);

        memberService.modifyMember(modifyMember);

        /* 로그인 시 저장 된 Authentication 객체를 변경 된 정보로 교체한다. 아래 메소드 참조 */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginMember.getMemberId()));

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        return "redirect:/";
    }

    private Authentication createNewAuthentication(String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }


    /* 회원 가입 */
    @PostMapping("/regist")
    public String registMember(MemberDTO member, String zipCode, String address1, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        String address = zipCode + "$" + address1 + "$" + address2;
        member.setAddress(address);
        member.setMemberPwd(passwordEncoder.encode(member.getPassword()));

        log.info("Request regist member : {}", member);

        memberService.registMember(member);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
        rttr.addFlashAttribute("nickname", member.getNickName());
        return "redirect:/member/completedRegist";
    }




}
