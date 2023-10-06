package com.mypet.petmily.petSitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetSitterProfileController {

    @GetMapping(value = "/petSitter/petSitterProfile")
    public String defaultLocation() {
        return "petSitter/petSitterProfile";
    }

}
