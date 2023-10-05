package com.mypet.petmily.member.controller;

import com.mypet.petmily.common.exception.member.MemberModifyException;
import com.mypet.petmily.common.exception.member.MemberPasswordUpdateException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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


    /* 회원 가입 메인 페이지 이동 */
    @GetMapping("/mainRegist")
    public void mainRegistPage(){}

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage(){}

    /* 회원 가입 완료 페이지 */
    @GetMapping("/completedRegist")
    public void comRegistPage(){}

    /* 회원 가입 */
    @PostMapping("/regist")
    public String registMember(MemberDTO member, String zipCode, String address1, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        String address = zipCode + "$" + address1 + "$" + address2;
        member.setAddress(address);
        member.setMemberPwd(passwordEncoder.encode(member.getPassword()));

        log.info("Request regist member : {}", member);

        memberService.registMember(member);


        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTime().getTime());

        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        member.setMemberStatDate(currentTimestamp);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
        rttr.addFlashAttribute("nickname", member.getNickName());
        return "redirect:/member/completedRegist";
    }

    /* 내 정보 확인 페이지로 이동 - 현재 로그인한 사용자의 정보를 받아온다. 객체는 MemberDTO.*/
    @GetMapping("/update")
    public void modifyPage(@AuthenticationPrincipal MemberDTO member){

    }


    /* 회원 정보 수정 */
    @PostMapping("/update")
    public String modifyMember(MemberDTO modifyMember, String postNo, String address, String address2,
                               @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr) throws MemberModifyException {

        String total_address = postNo + address + address2;
        modifyMember.setAddress(total_address);
        modifyMember.setMemberNo(loginMember.getMemberNo());

        log.info("modifyMember request Member : {}", modifyMember);

        memberService.modifyMember(modifyMember);

        /* 로그인 시 저장 된 Authentication 객체를 변경 된 정보로 교체한다. 아래 메소드 참조 */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginMember.getMemberId()));

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        return "redirect:/";
    }
    protected Authentication createNewAuthentication(String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }


    /* 패스워드 변경 페이지로 이동 */
    @GetMapping("/updatePassword")
    public void updatePasswordPage(@AuthenticationPrincipal MemberDTO member){}


    /* 패스워드 변경하기 */
    @PostMapping("/updatePassword")
    public String modifyPassword(MemberDTO modifyPassword,
                                 @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr,
                                  String updatePassword1,
                                  String updatePassword2) throws MemberPasswordUpdateException {

        if (updatePassword1.equals(updatePassword2)) {
            String hashedPassword = passwordEncoder.encode(updatePassword1);
            modifyPassword.setMemberId(loginMember.getMemberId());
            modifyPassword.setMemberPwd(hashedPassword);
            modifyPassword.setMemberNo(loginMember.getMemberNo());

            memberService.modifyPassword(modifyPassword);

            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(modifyPassword.getMemberId()));

            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify.success"));

        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify.password_missmatch"));
        }

        return "redirect:/member/update";
    }



    /* 로그인 화면 */
    @GetMapping("/login")
    public void loginPage(){}

    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr){
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
    }

    @GetMapping("/find_id-pwd")
    public void findIdPwdPage(){}

    @GetMapping("/pet-profile-regist")
    public void petProfileRegist(){}







}

