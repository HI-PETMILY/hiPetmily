package com.mypet.petmily.petSitter.controller;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.*;
import com.mypet.petmily.petSitter.service.PetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

        PetSitterDTO petSitterInfo = petSitterService.selectAllInfo(petSitterDTO);
        List<CareerDTO> careerList = petSitterService.selectAllCareer(petSitterDTO);
        List<PetTagDTO> petTagList = petSitterService.selectAllTag(petSitterDTO);

//        MemberDTO memberInfo = petSitterService.selectMemberInfo(petSitterDTO);

        log.info("--petSitterInfo : {}", petSitterInfo);
        log.info("--careerList : {}", careerList);
        log.info("--petTagList : {}", petTagList);
//        log.info("--444444 : {}", memberInfo);

        model.addAttribute("petSitterInfo", petSitterInfo);
        model.addAttribute("careerList", careerList);
        model.addAttribute("petTagList", petTagList);


        return "petSitter/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String ResRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitter/resRegistSuccess";
    }

    @PostMapping("/reservation")
    public String registReservation(PetSitterDTO petSitter, ReservationDTO reservation
            , RedirectAttributes rttr, @AuthenticationPrincipal MemberDTO member) throws PetSitterRegistException {

        // 테스트 회원정보랑 펫시터 정보 받기전에 임시코드
        petSitter.setPetMemberNo(4);

        /* ---- 추후에 페이지에서 멤버,펫시터 넘버 받아와야함 */
        reservation.setResMember(member);
        reservation.setResPetSitter(petSitter);

        reservation.setResStatus("대기");

        petSitterService.registReservation(reservation);

        log.info("-- reservation : {} ", reservation );

        // 회원정보랑 받아와야함 해야될거~~~
        rttr.addFlashAttribute("resCode", reservation.getResCode());
        rttr.addFlashAttribute("petMemberNo", reservation.getResPetSitter().getPetMemberNo());
        rttr.addFlashAttribute("startDateTime", reservation.getStartDateTime());
        rttr.addFlashAttribute("endDateTime", reservation.getEndDateTime());
        rttr.addFlashAttribute("resDayCount", reservation.getResDayCount());
        rttr.addFlashAttribute("timeCount", reservation.getResTimeCount());
        rttr.addFlashAttribute("totalAmount", reservation.getResTotalAmount());

        return "redirect:/petSitter/resRegistSuccess";
    }

    // 비동기
    @PostMapping("/petSitterSchedule")
    @ResponseBody
    public List<SitterScheduleDTO> petSitterSchedule(@RequestBody PetSitterDTO petSitter) {

        List<SitterScheduleDTO> sitterSchedule = petSitterService.petSitterSchedule(petSitter);

        log.info("--sitterSchedule : {}", sitterSchedule);

        return sitterSchedule;
    }

}
