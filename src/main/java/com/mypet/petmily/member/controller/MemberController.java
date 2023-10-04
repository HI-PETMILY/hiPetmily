package com.mypet.petmily.member.controller;

import com.mypet.petmily.common.exception.member.MemberModifyException;
import com.mypet.petmily.common.exception.member.MemberRegistException;
import com.mypet.petmily.member.dto.MailDTO;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.member.service.MailService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final MailService mailService;


    public MemberController(MemberService memberService,
                            AuthenticationService authenticationService,
                            MessageSourceAccessor messageSourceAccessor,
                            PasswordEncoder passwordEncoder,
                            MailService mailService) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
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


    /* 내 정보 확인 */
    @GetMapping("/myInfo")
    public void modifyPage(){} /* 로그인 시 멤버 정보 불러오기 짜야함 */


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
    protected Authentication createNewAuthentication(String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }

    /* 로그인 */
    @GetMapping("/login")
    public void loginPage(){}

    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr){
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
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


        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTime().getTime());

        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        member.setMemberStatDate(currentTimestamp);


        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
        rttr.addFlashAttribute("nickname", member.getNickName());
        return "redirect:/member/completedRegist";
    }

    /* 아이디 비밀번호 찾기 화면 */
    @GetMapping("/find_id-pwd")
    public void findIdPwdPage(){}

    /*아이디 찾기 결과 */
    @PostMapping("/find_id-pwd")
    public String findId(@RequestParam("memberName") String memberName,
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

    @PostMapping("/find_pwd")   //폼 액션
    /* 비밀번호 찾기 */
    public String sendEmail(@RequestParam("memberId") String email){

        MailDTO dto = mailService.createEmailContent(email);
        mailService.mailSend(dto);

        return "member/login";
    }






    @GetMapping("/pet-profile-regist")
    public void petProfileRegist(){}
}

