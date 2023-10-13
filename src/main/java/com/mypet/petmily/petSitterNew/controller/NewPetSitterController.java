package com.mypet.petmily.petSitterNew.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitterNew.dto.*;
import com.mypet.petmily.petSitterNew.service.NewPetSitterService;
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
@RequestMapping("/petSitterNew")
public class NewPetSitterController {

    private final MessageSourceAccessor messageSourceAccessor;
    private final NewPetSitterService newPetSitterService;

    public NewPetSitterController(MessageSourceAccessor messageSourceAccessor, NewPetSitterService newPetSitterService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.newPetSitterService = newPetSitterService;
    }


    @GetMapping("/regist")
    public String petSitterRegist(){

        return  "petSitterNew/petSitterRegist";
    }

    @GetMapping(value = "/petSitterProfile")
//    public String PetSitterProfile(PetSitterDTO2 petSitterDTO2, Model model, @AuthenticationPrincipal MemberDTO member) {
    public String petSitterProfile(NewPetSitterDTO petSitter, Model model) {

        NewPetSitterDTO petSitterInfo = newPetSitterService.selectAllInfo(petSitter);
        List<CareerDTO> careerList = newPetSitterService.selectAllCareer(petSitter);
        List<PetTagDTO> petTagList = newPetSitterService.selectAllTag(petSitter);

        PetJsonMemberDTO petJsonMemberInfo = newPetSitterService.selectMemberInfo(petSitter);

        log.info("--petSitterInfo : {}", petSitterInfo);
        log.info("--careerList : {}", careerList);
        log.info("--petTagList : {}", petTagList);
        log.info("--petJsonMemberInfo : {}", petJsonMemberInfo);

        model.addAttribute("petSitterInfo", petSitterInfo);
        model.addAttribute("careerList", careerList);
        model.addAttribute("petTagList", petTagList);
        model.addAttribute("memberInfo", petJsonMemberInfo);


        return "petSitterNew/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String resRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitterNew/resRegistSuccess";
    }

    @PostMapping("/reservation")
    public String registReservation(ReservationDTO reservation,
                                    RedirectAttributes rttr, @AuthenticationPrincipal MemberDTO member) {

        NewPetSitterDTO petSitter = new NewPetSitterDTO();

        // 테스트 회원정보랑 펫시터 정보 받기전에 임시코드
        /* ---- 추후에 펫시터 넘버 받아와야함 */
        petSitter.setPetMemberNo(4);

        reservation.setResMember(member);
        reservation.setResPetSitter(petSitter);

        reservation.setResStatus("대기");

        newPetSitterService.registReservation(reservation);

        log.info("-- reservation : {} ", reservation );

        rttr.addFlashAttribute("resCode", reservation.getResCode());
        rttr.addFlashAttribute("petMemberNo", reservation.getResPetSitter().getPetMemberNo());
        rttr.addFlashAttribute("startDateTime", reservation.getStartDateTime());
        rttr.addFlashAttribute("endDateTime", reservation.getEndDateTime());
        rttr.addFlashAttribute("resDayCount", reservation.getResDayCount());
        rttr.addFlashAttribute("timeCount", reservation.getResTimeCount());
        rttr.addFlashAttribute("totalAmount", reservation.getResTotalAmount());

        return "redirect:/petSitterNew/resRegistSuccess";
    }

    // 펫시터 스케줄 비동기
    @PostMapping("/petSitterSchedule")
    @ResponseBody
    public List<SitterScheduleDTO> petSitterSchedule(@RequestBody NewPetSitterDTO petSitter) {

        List<SitterScheduleDTO> sitterSchedule = newPetSitterService.petSitterSchedule(petSitter);

        // 해당 비동기 호출 2번(달력 2가지)
        log.info("--sitterSchedule : {}", sitterSchedule);

        return sitterSchedule;
    }

    // 카카오 지도 비동기
    @PostMapping("/petSitterAddress")
    @ResponseBody
    public PetJsonMemberDTO petSitterAddress(@RequestBody NewPetSitterDTO petSitter) {

        PetJsonMemberDTO petJsonMember = newPetSitterService.petSitterAddress(petSitter);

        log.info("--petSitterAddress : {}", petJsonMember);

        return petJsonMember;
    }

}
