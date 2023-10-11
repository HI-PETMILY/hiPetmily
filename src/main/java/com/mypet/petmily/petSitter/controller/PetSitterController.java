package com.mypet.petmily.petSitter.controller;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import com.mypet.petmily.petSitter.service.PetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String PetSitterProfile() {

        return "petSitter/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String ResRegistSuccess() {

        return "petSitter/resRegistSuccess";
    }

    @PostMapping("/reservation")
    public String registReservation(ReservationDTO reservation
            , RedirectAttributes rttr ) throws PetSitterRegistException {

//        int searchResNo = 0;

        // 테스트
        MemberDTO memberDTO = new MemberDTO();
        PetSitterDTO petSitterDTO = new PetSitterDTO();
        memberDTO.setMemberNo(4);
        petSitterDTO.setPetMemberNo(4);
        log.info("----- 33333333333333333333");


        /* ---- 추후에 페이지에서 멤버,펫시터 넘버 받아와야함 */
//        reservation.getResMember().setMemberNo(4);
//        reservation.getResPetSitter().setPetMemberNo(4);
        reservation.setResMember(memberDTO);
        reservation.setResPetSitter(petSitterDTO);
        log.info("2222222222222 : {}", reservation.getResMember().getMemberNo());
        log.info("333434322222 : {}", reservation.getResPetSitter().getPetMemberNo());

//        reservation.setResMemberNo(4);
//        reservation.setResPetMemberNo(4);
        reservation.setResStatus("대기");

        log.info("444----- reservation : {}", reservation);

        petSitterService.registReservation(reservation);

        log.info("5555------0-0312424-=-- : {} ", reservation );

        // 회원정보랑 예약번호 받아와야함 해야될거~~~
        rttr.addFlashAttribute("resCode", reservation.getResCode());
        rttr.addFlashAttribute("startDateTime", reservation.getStartDateTime());
        rttr.addFlashAttribute("endDateTime", reservation.getEndDateTime());
        rttr.addFlashAttribute("resDayCount", reservation.getResDayCount());
        rttr.addFlashAttribute("timeCount", reservation.getResTimeCount());
        rttr.addFlashAttribute("totalAmount", reservation.getResTotalAmount());

        return "redirect:/petSitter/resRegistSuccess";
    }



}
