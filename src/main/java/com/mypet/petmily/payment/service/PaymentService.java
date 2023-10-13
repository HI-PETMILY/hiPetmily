package com.mypet.petmily.payment.service;



import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dao.PaymentMapper;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public List<ReservationDTO> selectReservationHistoryList(MemberDTO loginMember) {
        return paymentMapper.selectReservationHistoryList(loginMember);


    }

    /* 진행 중인 예약 조회 페이지 */
    public List<ProgressReserveDTO> selectProgressReserveList(MemberDTO loginMember) {
        return paymentMapper.selectProgressReserveList(loginMember);
    }

}
