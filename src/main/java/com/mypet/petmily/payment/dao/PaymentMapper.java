package com.mypet.petmily.payment.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

    /* 예약 조회 페이징 */
    int selectReservationTotalCount();

    // List<ReservationDTO> selectReservationBoardList(int memberNo, SelectCriteria selectCriteria);
    // 파라미터 2개 이상 넘기기 위해서 @Param 사용
    List<ProgressReserveDTO> selectReservationBoardList(@Param("memberNo") int memberNo, @Param("startRow") int startRow, @Param("endRow") int endRow);

    List<ProgressReserveDTO> selectDetailReservation(MemberDTO loginMember, int resCode);

    List<ProgressReserveDTO> selectProgressReserveList(MemberDTO loginMember);
}
