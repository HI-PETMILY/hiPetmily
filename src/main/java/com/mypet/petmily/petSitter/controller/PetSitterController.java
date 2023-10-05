package com.mypet.petmily.petSitter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
