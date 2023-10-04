package com.mypet.petmily.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CouplingController {

    @GetMapping("/main-member")
    public String getAdminPage(){

        return "/admin/member/main-member";
    }

    @GetMapping("/secession")
    public String getSecessionPage(){

        return "admin/member/secession";
    }

    @GetMapping("/board")
    public String getBoardPage(){

        return "admin/member/board";
    }

    @GetMapping("/comment")
    public String getCommentPage(){

        return "admin/member/comment";
    }

    @GetMapping("/applyform")
    public String getApplyFormPage(){

        return "admin/member/applyform";
    }

    @GetMapping("/management")
    public String getApplicationPage(){

        return "admin/member/management";
    }

    @GetMapping("/reservation")
    public String getReservationPage(){

        return "admin/member/reservation";
    }

    @GetMapping("/automail")
    public String getAutoMailPage(){

        return "admin/MailManagement/automail";
    }

    @GetMapping("/atmosphere")
    public String getAtmospherePage(){

        return "admin/statistics/atmosphere";
    }

    @GetMapping("/complete")
    public String getCompletePage(){

        return "admin/statistics/complete";
    }

    @GetMapping("/sales")
    public String getSalesPage(){

        return "admin/statistics/sales";
    }


}
