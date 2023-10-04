package com.mypet.petmily.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ExampleController {

    @GetMapping("/main-member")
    public String getAdminPage(){

        return "admin/main-member";
    }

    @GetMapping("/secession")
    public String getSecessionPage(){

        return "admin/secession";
    }

    @GetMapping("/board")
    public String getBoardPage(){

        return "admin/board";
    }

    @GetMapping("/comment")
    public String getCommentPage(){

        return "admin/comment";
    }

    @GetMapping("/applyform")
    public String getApplyFormPage(){

        return "admin/applyform";
    }

    @GetMapping("/application")
    public String getApplicationPage(){

        return "admin/application";
    }

    @GetMapping("/reservation")
    public String getReservationPage(){

        return "admin/reservation";
    }

    @GetMapping("/automail")
    public String getAutoMailPage(){

        return "admin/automail";
    }

    @GetMapping("/atmosphere")
    public String getAtmospherePage(){

        return "admin/atmosphere";
    }

    @GetMapping("/complete")
    public String getCompletePage(){

        return "admin/complete";
    }

    @GetMapping("/sales")
    public String getSalesPage(){

        return "admin/sales";
    }


}
