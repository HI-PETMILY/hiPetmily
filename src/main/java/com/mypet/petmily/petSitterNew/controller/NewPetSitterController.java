package com.mypet.petmily.petSitterNew.controller;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitterNew.dto.*;
import com.mypet.petmily.petSitterNew.service.NewPetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petSitterNew")
public class NewPetSitterController {

    private final NewPetSitterService newPetSitterService;

    public NewPetSitterController(NewPetSitterService newPetSitterService) {
        this.newPetSitterService = newPetSitterService;
    }

    @GetMapping(value = "/petSitterProfile")
    public String PetSitterProfile(NewPetSitterDTO petSitter, Model model, @AuthenticationPrincipal MemberDTO member) {

        petSitter = newPetSitterService.petSitterProfile(petSitter);

        log.info("--afterPetSitterDTO : {}", petSitter);

        model.addAttribute("petSitterInfo", petSitter );
        model.addAttribute("sitterImgList", petSitter.getSitterImgList());
        model.addAttribute("careerList", petSitter.getCareerList());
        model.addAttribute("petTagList", petSitter.getPetTagList());
        model.addAttribute("memberInfo", petSitter.getPetJsonMemberInfo());
        model.addAttribute("myPetList", petSitter.getMyPetList());
        model.addAttribute("reviewList", petSitter.getReviewList());

        return "petSitterNew/petSitterProfile";
    }

    @GetMapping(value = "/resRegistSuccess")
    public String resRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitterNew/resRegistSuccess";
    }

    @GetMapping(value = "/sitterRegistSuccess")
    public String sitterRegistSuccess(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitterNew/sitterRegistSuccess";
    }

    @GetMapping(value = "/sitterRegistFail")
    public String sitterRegistFail(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member);

        return "petSitterNew/sitterRegistFail";
    }

    @PostMapping("/regist")
    public String petSitterRegist(NewPetSitterDTO petSitter, RedirectAttributes rttr
            , List<MultipartFile> attachImage, @AuthenticationPrincipal MemberDTO member
            , @RequestParam(value = "tagContent", required = false) List<String> petTagList
            , @RequestParam(value = "careerContent", required = false) List<String> careerList
            , @RequestParam(value = "petMemberResDay", required = false) String schedule ) throws PetSitterRegistException {

        int memberNo = member.getMemberNo();
        // 로그인한 회원넘버 == 펫시터넘버
        petSitter.setPetMemberNo(memberNo);


        // 펫시터 중복체크
        if ( newPetSitterService.petSitterCheck( petSitter ) ) {

            petSitter = newPetSitterService.petSitterStat( petSitter );
            rttr.addFlashAttribute("getPetStat", petSitter.getPetStat());

            // 중복가입 안내페이지
            return "redirect:/petSitterNew/sitterRegistFail";

        } else {

            // 태그 정보
            petSitter.setRegPetTagList(petTagList);
            // 경력 정보
            petSitter.setRegCareerList(careerList);
            // 펫시터 예약불가 스케줄
            petSitter.setSchedule(schedule);
            // 이미지 첨부파일
            petSitter.setAttachImage(attachImage);

            // 통합 등록
            newPetSitterService.petSitterRegist(petSitter);

            return "redirect:/petSitterNew/sitterRegistSuccess";

        }

    }

    @PostMapping("/reservation")
    public String registReservation(ReservationDTO reservation,
                                    RedirectAttributes rttr, @AuthenticationPrincipal MemberDTO member) {

        NewPetSitterDTO petSitter = new NewPetSitterDTO();

        if ( member != null ) {

            // 예약을 받는 펫시터 번호
            petSitter.setPetMemberNo(reservation.getResSitterNo());

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

        } else {

            log.info("-- 비 로그인 상태 : {}", member);
            return "redirect:/";
        }

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
