package com.mypet.petmily.petSitter.controller;


import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.MemberRoleDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.ReviewDTO;
import com.mypet.petmily.petSitter.service.PetSitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/petSitter")
public class PetSitterController {

//    private final MypageService mypageService;
//
//    public MypageController(MypageService mypageService){
//
//        this.mypageService = mypageService;
//    }



    @GetMapping("/mypage")
    public String getMypage(Model model){



        return "petSitter/mypage";



    }


    @GetMapping("/account")
    public String getAccount(Model model){

        return  "petSitter/account";
    }

    @GetMapping("/reservationList")

    public  String getReservationList(Model model){

        return "petSitter/reservationList";
    }


    private  final PetSitterService petSitterService;

    public PetSitterController(PetSitterService petSitterService){

        this.petSitterService= petSitterService;
    }

//    @GetMapping("/searchPage")
//
//    public  String getPetSitterList(@RequestParam(defaultValue ="1") int page,
//                                    @RequestParam(required = false) String searchCondition,
//                                    @RequestParam(required = false) String searchValue){
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
//        Map<String, Object> petSitterListAndPaging  = petSitterService.selectPetSitterList(searchMap, page);
//
//
//        return "petSitter/searchPage";
//    }

    @GetMapping("/searchPage")

    public  String selectPetSitterList(Model model){

        List<PetSitterDTO> petSitterDTOList = petSitterService.selectPetSitterList();
        model.addAttribute("petSitterList",petSitterDTOList);

        log.info("petSitterList : {}", petSitterDTOList);
        return "petSitter/searchPage";
    }






}
