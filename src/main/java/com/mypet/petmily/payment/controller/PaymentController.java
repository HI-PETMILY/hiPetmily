package com.mypet.petmily.payment.controller;


import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import com.mypet.petmily.payment.service.PaymentService;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
public class PaymentController {

    private final PaymentService paymentService;
    private final MessageSourceAccessor messageSourceAccessor;

    public PaymentController(PaymentService paymentService, MessageSourceAccessor messageSourceAccessor) {
        this.paymentService = paymentService;
        this.messageSourceAccessor = messageSourceAccessor;
    }


    /* 지난 예약 조회로 이동 */
    @GetMapping("/reservationList")
    public void reservationHistoryPage(@AuthenticationPrincipal MemberDTO loginMember,
                                       Model model, @ModelAttribute ReservationHistoryDTO selectReserve) {

//        selectReserve.setResMember(loginMember);

        model.addAttribute("loginMember", loginMember.getMemberNo());
        log.info("loginMember : {}", loginMember);
        List<ReservationDTO> reservationHistoryList = paymentService.selectReservationHistoryList(loginMember);
        log.info("reservationHistoryList : {}", reservationHistoryList);
        model.addAttribute("reservationHistoryList", reservationHistoryList);

    }
}
