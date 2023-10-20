package com.mypet.petmily.payment.service;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.Pagenation.Pagenation;
import com.mypet.petmily.payment.Pagenation.SelectCriteria;
import com.mypet.petmily.payment.dao.PaymentMapper;
import com.mypet.petmily.payment.dto.PaymentDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }


    /* 지난 예약 조회 */
    public Map<String, Object> selectReservationList(int memberNo, int page) {

        int totalCount = paymentMapper.selectReservationTotalCount();
        log.info("reservation totalCount : {}", totalCount);

        int limit= 5;
        int buttonAmount = 3;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);

        List<ProgressReserveDTO> reservationList = paymentMapper.selectReservationBoardList(
                memberNo, selectCriteria.getStartRow(), selectCriteria.getEndRow());

        Map<String, Object> reservationListAndPaging = new HashMap<>();
        reservationListAndPaging.put("paging", selectCriteria);
        reservationListAndPaging.put("reservationList", reservationList);
        return reservationListAndPaging;
    }


    /* 상세 내역 조회 - 예약, 펫시터 */
    public List<ProgressReserveDTO> selectDetailReservation(MemberDTO loginMember, int reserveCode) {
        return paymentMapper.selectDetailReservation(loginMember, reserveCode);
    }

    /* 상세 내역 조회 - 결제내역 */
    public List<PaymentDTO> selectPaymentReservation(MemberDTO loginMember, int reserveCode) {
        return paymentMapper.selectPaymentReservation(loginMember, reserveCode);
    }


    /* 예약 조회 */
//    public List<ReservationDTO> selectReservationHistoryList(MemberDTO loginMember) {
//        return paymentMapper.selectReservationHistoryList(loginMember);
//
//    }

    /* 예약 조회 페이징 */
//    public Map<String, Object> selectReservationList(int page) {
//
//        int totalCount = paymentMapper.selectReservationTotalCount();
//        log.info("reservation totalcount : {}", totalCount);
//
//        int limit= 5;
//        int buttonAmount = 5;
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);
//        log.info("reservation selectCriteria : {}", selectCriteria);
//
//        /* 체크 */
//        List<ReservationDTO> reservationList = paymentMapper.selectReservationBoardList(selectCriteria);
//        log.info("reservation reservationList : {}", reservationList);
//
//        Map<String, Object> reservationListAndPaging = new HashMap<>();
//        reservationListAndPaging.put("paging", selectCriteria);
//        reservationListAndPaging.put("reservationList", reservationList);
//
//        return reservationListAndPaging;
//    }

    /* 진행 중인 예약 조회 페이지 */
    public List<ProgressReserveDTO> selectProgressReserveList(MemberDTO loginMember) {
        return paymentMapper.selectProgressReserveList(loginMember);
    }



}
