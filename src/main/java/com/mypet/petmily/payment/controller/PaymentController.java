package com.mypet.petmily.payment.controller;


import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.payment.service.PaymentService;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/member")
public class PaymentController {

    private final PaymentService paymentService;
    private final AuthenticationService authenticationService;
    private final MessageSourceAccessor messageSourceAccessor;

    public PaymentController(PaymentService paymentService, AuthenticationService authenticationService, MessageSourceAccessor messageSourceAccessor) {
        this.paymentService = paymentService;
        this.authenticationService = authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
    }


    /* 지난 예약 조회로 이동 */
    @GetMapping("/reservationList")
    public void reservationHistroyPage(@AuthenticationPrincipal MemberDTO loginMember, Model model) {

        model.addAttribute("loginMember", loginMember.getMemberId());

        List<ReservationDTO> reservationHistroyList = paymentService.selectReservationHistroyList(loginMember);

        model.addAttribute("loginMember", reservationHistroyList);

    }
}
