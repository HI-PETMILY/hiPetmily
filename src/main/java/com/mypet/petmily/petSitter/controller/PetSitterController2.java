package com.mypet.petmily.petSitter.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.*;
import com.mypet.petmily.petSitter.service.PetSitterService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petSitter")
public class PetSitterController2 {

    private final MessageSourceAccessor messageSourceAccessor;
    private final PetSitterService2 petSitterService2;

    public PetSitterController2(MessageSourceAccessor messageSourceAccessor, PetSitterService2 petSitterService2) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.petSitterService2 = petSitterService2;
    }


    @GetMapping("/mypage")
    public String getMypage(Model model){

        return "petSitter/mypage";
    }

    @GetMapping("/account")
    public String getAccount(Model model){

        return  "petSitter/account";
    }

    @GetMapping("/regist")
    public String petSitterRegist(){

        return  "petSitter/petSitterRegist";
    }

    @GetMapping(value = "/petSitterProfile")
//    public String PetSitterProfile(PetSitterDTO2 petSitterDTO2, Model model, @AuthenticationPrincipal MemberDTO member) {
    public String petSitterProfile(PetSitterDTO2 petSitter, Model model) {

        PetSitterDTO2 petSitterInfo = petSitterService2.selectAllInfo(petSitter);
        List<CareerDTO> careerList = petSitterService2.selectAllCareer(petSitter);
        List<PetTagDTO> petTagList = petSitterService2.selectAllTag(petSitter);

        PetJsonMemberDTO petJsonMemberInfo = petSitterService2.selectMemberInfo(petSitter);

        log.info("--petSitterInfo : {}", petSitterInfo);
        log.info("--careerList : {}", careerList);
        log.info("--petTagList : {}", petTagList);
        log.info("--petJsonMemberInfo : {}", petJsonMemberInfo);

        model.addAttribute("petSitterInfo", petSitterInfo);
        model.addAttribute("careerList", careerList);
        model.addAttribute("petTagList", petTagList);
        model.addAttribute("memberInfo", petJsonMemberInfo);


        return "petSitter/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String resRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitter/resRegistSuccess";
    }

    @PostMapping("/reservation")
    public String registReservation(ReservationDTO reservation,
                                    RedirectAttributes rttr, @AuthenticationPrincipal MemberDTO member) {

        PetSitterDTO2 petSitter = new PetSitterDTO2();

        // 테스트 회원정보랑 펫시터 정보 받기전에 임시코드
        /* ---- 추후에 펫시터 넘버 받아와야함 */
        petSitter.setPetMemberNo(4);

        reservation.setResMember(member);
        reservation.setResPetSitter(petSitter);

        reservation.setResStatus("대기");

        petSitterService2.registReservation(reservation);

        log.info("-- reservation : {} ", reservation );

        rttr.addFlashAttribute("resCode", reservation.getResCode());
        rttr.addFlashAttribute("petMemberNo", reservation.getResPetSitter().getPetMemberNo());
        rttr.addFlashAttribute("startDateTime", reservation.getStartDateTime());
        rttr.addFlashAttribute("endDateTime", reservation.getEndDateTime());
        rttr.addFlashAttribute("resDayCount", reservation.getResDayCount());
        rttr.addFlashAttribute("timeCount", reservation.getResTimeCount());
        rttr.addFlashAttribute("totalAmount", reservation.getResTotalAmount());

        return "redirect:/petSitter/resRegistSuccess";
    }

    // 펫시터 스케줄 비동기
    @PostMapping("/petSitterSchedule")
    @ResponseBody
    public List<SitterScheduleDTO> petSitterSchedule(@RequestBody PetSitterDTO2 petSitter) {

        List<SitterScheduleDTO> sitterSchedule = petSitterService2.petSitterSchedule(petSitter);

        // 해당 비동기 호출 2번(달력 2가지)
        log.info("--sitterSchedule : {}", sitterSchedule);

        return sitterSchedule;
    }

    // 카카오 지도 비동기
    @PostMapping("/petSitterAddress")
    @ResponseBody
    public PetJsonMemberDTO petSitterAddress(@RequestBody PetSitterDTO2 petSitter) {

        PetJsonMemberDTO petJsonMember = petSitterService2.petSitterAddress(petSitter);

        log.info("--petSitterAddress : {}", petJsonMember);

        return petJsonMember;
    }

}
