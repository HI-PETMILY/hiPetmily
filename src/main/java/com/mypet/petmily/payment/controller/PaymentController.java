package com.mypet.petmily.payment.controller;


import com.mypet.petmily.common.exception.member.reservationDetailException;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import com.mypet.petmily.payment.service.PaymentService;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


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
//    @GetMapping("/reservationList")
//    public String reservationHistoryPage(@AuthenticationPrincipal MemberDTO loginMember,
//                                         @RequestParam(defaultValue = "1") int page,
//                                       @ModelAttribute ReservationHistoryDTO selectReserve,
//                                       Model model) {
//
//        /* 페이징 */
//        Map<String, Object> ReservationListAndPaging = paymentService.selectReservationList(page);
//        model.addAttribute("paging", ReservationListAndPaging.get("paging"));
//        model.addAttribute("reservationList", ReservationListAndPaging.get("reservationList"));
////        model.addAttribute("loginMember", ReservationListAndPaging.get(loginMember.getMemberNo()));
//
//        /* 예약 정보 조회 */
//        model.addAttribute("loginMember", loginMember.getMemberNo());
//        log.info("loginMember : {}", loginMember);
//        List<ReservationDTO> reservationHistoryList = paymentService.selectReservationHistoryList(loginMember);
//        log.info("reservationHistoryList : {}", reservationHistoryList);
//        model.addAttribute("reservationHistoryList", reservationHistoryList);
//
//
//        return "member/reservationList";
//    }

    /* 지난 예약 */

    @GetMapping("/reservationList")
    public String listReservations(@AuthenticationPrincipal MemberDTO loginMember,
                                   @RequestParam(defaultValue = "1") int page,
                                   Model model) {
        Map<String, Object> ReservationListAndPaging = paymentService.selectReservationList(loginMember.getMemberNo(), page);
        model.addAttribute("paging", ReservationListAndPaging.get("paging"));
        model.addAttribute("reservationList", ReservationListAndPaging.get("reservationList"));
//        model.addAttribute("startRow", ReservationListAndPaging.get("startRow"));
//        model.addAttribute("endRow", ReservationListAndPaging.get("endRow"));
        log.info("reservationList : {}", ReservationListAndPaging.get("reservationList"));

        return "/member/reservationlist";
    }


    @GetMapping("reservationDetail")
    public String detailReservation(@AuthenticationPrincipal MemberDTO loginMember, Model model,
                                    @RequestParam int resCode, RedirectAttributes rttr)
    throws reservationDetailException {

        model.addAttribute("loginMember", loginMember.getMemberNo());
        log.info("loginMember : {}", loginMember);
        List<ReservationDTO> detailReservation = paymentService.selectDetailReservation(loginMember, resCode);
        model.addAttribute("detailReservation", detailReservation);
        log.info("detailReservation : {}", detailReservation);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("res.detail.select"));

        return "member/reservationDetail";
    }

    /* 진행 중인 예약 리스트 조회 페이지 */
    @GetMapping("/reservation-in-progress")
    public void reservationInProgressPage(@AuthenticationPrincipal MemberDTO loginMember, Model model){

        List<ProgressReserveDTO> progressReserveList = paymentService.selectProgressReserveList(loginMember);

        model.addAttribute("progressReserveList", progressReserveList);
    }
}
