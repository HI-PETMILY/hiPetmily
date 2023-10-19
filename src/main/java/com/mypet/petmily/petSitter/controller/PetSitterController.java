package com.mypet.petmily.petSitter.controller;


import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.service.MypageService;
import com.mypet.petmily.petSitter.service.PetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petSitter")
public class PetSitterController {


    private final MypageService mypageService;


    private final PetSitterService petSitterService;


    public PetSitterController(MypageService mypageService, PetSitterService petSitterService) {
        this.mypageService = mypageService;
        this.petSitterService = petSitterService;
    }


    @GetMapping("/mypage")
    public String getMypage() {


        return "petSitter/mypage";


    }


//    @GetMapping("/account")
//    public String getAccount(@AuthenticationPrincipal MemberDTO loginMember, Model model) {
//        PetSitterDTO petSitterInfo= petSitterService.getAccount(loginMember);
//        model.addAttribute("petSitter"  ,petSitterInfo);
//        return "petSitter/account";
//    }




    @GetMapping("/reservationList")

    public String getReservationList(Model model) {

        return "petSitter/reservationList";
    }



    @GetMapping("/searchPage")
    public String getPetSitterList(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(required = false) String searchCondition,
                                   @RequestParam(required = false) String searchValue,
                                   Model model) {
//
//        log.info("petsitterList page : {}", page);
//        log.info("petsitterList searchCondition : {}", searchCondition);
//        log.info("petsitterList searchValue : {}", searchValue);
//
//        //검색값으로 조회
//        Map<String, String> searchMap = new HashMap<>();
//        searchMap.put("searchCondition", searchCondition);
//        searchMap.put("searchValue", searchValue);
//
//
//        Map<String, Object> petSitterListAndPaging = petSitterService.selectPetSitterPaging(searchMap, page);
//        model.addAttribute("paing", petSitterListAndPaging.get("paging"));
//        model.addAttribute("petSitterList", petSitterListAndPaging.get("petSitterList"));

        List<PetSitterDTO> petSitterDTOList = petSitterService.selectPetSitterList();
        model.addAttribute("petSitterList", petSitterDTOList);

        log.info("petSitterList : {}", petSitterDTOList);


        return "petSitter/searchPage";
    }
}


