package com.mypet.petmily.petSitter.controller;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import com.mypet.petmily.petSitter.service.PetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petSitter")
public class PetSitterController {

    private final MessageSourceAccessor messageSourceAccessor;
    private final PetSitterService petSitterService;

    public PetSitterController(MessageSourceAccessor messageSourceAccessor, PetSitterService petSitterService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.petSitterService = petSitterService;
    }


    @GetMapping("/mypage")
    public String getMypage(Model model){

        return "petSitter/mypage";
    }

    @GetMapping("/account")
    public String getAccount(Model model){

        return  "petSitter/account";
    }

    @GetMapping(value = "/petSitterProfile")
//    public String PetSitterProfile(PetSitterDTO petSitterDTO, Model model, @AuthenticationPrincipal MemberDTO member) {
    public String PetSitterProfile(PetSitterDTO petSitterDTO, Model model) {

        log.info("--3232323 : {}", petSitterDTO.getPetMemberNo());

        PetSitterDTO petSitterInfo = petSitterService.selectAllInfo(petSitterDTO);

        log.info("----4444444 : {}", petSitterInfo);

        model.addAttribute("petSitterInfo", petSitterInfo);

//        log.info("1로그인 한 유저의 번호 : {}", member.getMemberNo());

        return "petSitter/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String ResRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        log.info("2로그인 한 유저의 번호 : {}", member.getMemberNo());
        log.info("2222로그인 한 유저의 번호 : {}", member.getNickName());

        model.addAttribute("member", member);

        return "petSitter/resRegistSuccess";
    }

    @PostMapping("/reservation")
    public String registReservation(ReservationDTO reservation
            , RedirectAttributes rttr, @AuthenticationPrincipal MemberDTO member) throws PetSitterRegistException {

        // 테스트 회원정보랑 펫시터 정보 받기전에 임시코드
        PetSitterDTO petSitterDTO = new PetSitterDTO();
        petSitterDTO.setPetMemberNo(4);

        log.info("3로그인 한 유저의 번호 : {}", member.getMemberNo());
        log.info("3333로그인 한 유저의 번호 : {}", member.getNickName());

        /* ---- 추후에 페이지에서 멤버,펫시터 넘버 받아와야함 */
        reservation.setResMember(member);
        reservation.setResPetSitter(petSitterDTO);

        reservation.setResStatus("대기");

        petSitterService.registReservation(reservation);

        log.info("5555------ reservation : {} ", reservation );

        // 회원정보랑 받아와야함 해야될거~~~
        rttr.addFlashAttribute("resCode", reservation.getResCode());
        rttr.addFlashAttribute("startDateTime", reservation.getStartDateTime());
        rttr.addFlashAttribute("endDateTime", reservation.getEndDateTime());
        rttr.addFlashAttribute("resDayCount", reservation.getResDayCount());
        rttr.addFlashAttribute("timeCount", reservation.getResTimeCount());
        rttr.addFlashAttribute("totalAmount", reservation.getResTotalAmount());

        return "redirect:/petSitter/resRegistSuccess";
    }



}
