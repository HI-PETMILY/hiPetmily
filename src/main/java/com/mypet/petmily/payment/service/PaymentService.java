package com.mypet.petmily.payment.service;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.Pagenation.Pagenation;
import com.mypet.petmily.payment.Pagenation.SelectCriteria;
import com.mypet.petmily.payment.dao.PaymentMapper;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Map<String, Object> selectReservationList(int memberNo, int page) {

        int totalCount = paymentMapper.selectReservationTotalCount();
        log.info("reservation totalCount : {}", totalCount);

        int limit= 5;
        int buttonAmount = 3;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);
        log.info("reservation selectCriteria : {}", selectCriteria);

        List<ReservationDTO> reservationList = paymentMapper.selectReservationBoardList(memberNo, selectCriteria.getStartRow(), selectCriteria.getEndRow());
        log.info("reservation reservationList : {}", reservationList);

        Map<String, Object> reservationListAndPaging = new HashMap<>();
        reservationListAndPaging.put("paging", selectCriteria);
        reservationListAndPaging.put("reservationList", reservationList);
        log.info("reservationListAndPaging : {}", reservationListAndPaging);
        return reservationListAndPaging;
    }

    public List<ReservationDTO> selectDetailReservation(MemberDTO loginMember, int resCode) {
        return paymentMapper.selectDeatilReservation(loginMember, resCode);
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
