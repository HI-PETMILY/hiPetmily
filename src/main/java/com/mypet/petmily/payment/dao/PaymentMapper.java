package com.mypet.petmily.payment.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.PaymentDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

    /* 예약 조회 페이징 */
    int selectReservationTotalCount();

    /* 지난 예약 조회 */
    List<ProgressReserveDTO> selectReservationBoardList(@Param("memberNo") int memberNo, @Param("startRow") int startRow, @Param("endRow") int endRow);

    /* 예약 상세 내역 조회 */
    List<ProgressReserveDTO> selectDetailReservation(MemberDTO loginMember, int reserveCode);

    /* 진행중인 예약 조회 */
    List<ProgressReserveDTO> selectProgressReserveList(MemberDTO loginMember);

    /* 예약 상세 내역 조회 - 결제 내역 */
    List<PaymentDTO> selectPaymentReservation(MemberDTO loginMember, int reserveCode);
}
