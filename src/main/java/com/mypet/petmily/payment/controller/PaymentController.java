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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/member")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    /* 지난 예약 조회로 이동 */
    @GetMapping("/reservationList")
    public String reservationHistoryPage(@AuthenticationPrincipal MemberDTO loginMember,
                                         @RequestParam(defaultValue = "1") int page,
                                       @ModelAttribute ReservationHistoryDTO selectReserve,
                                       Model model) {

        /* 페이징 */
        Map<String, Object> ReservationListAndPaging = paymentService.selectReservationList(page);
        model.addAttribute("paging", ReservationListAndPaging.get("paging"));
        model.addAttribute("reservationList", ReservationListAndPaging.get("reservationList"));
//        model.addAttribute("loginMember", ReservationListAndPaging.get(loginMember.getMemberNo()));

        /* 예약 정보 조회 */
        model.addAttribute("loginMember", loginMember.getMemberNo());
        log.info("loginMember : {}", loginMember);
        List<ReservationDTO> reservationHistoryList = paymentService.selectReservationHistoryList(loginMember);
        log.info("reservationHistoryList : {}", reservationHistoryList);
        model.addAttribute("reservationHistoryList", reservationHistoryList);


        return "member/reservationList";
    }
}
