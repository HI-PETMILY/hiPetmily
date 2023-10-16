package com.mypet.petmily.payment.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import com.mypet.petmily.payment.Pagenation.SelectCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

    /* 예약 조회 페이징 */
    int selectReservationTotalCount();

    // List<ReservationDTO> selectReservationBoardList(int memberNo, SelectCriteria selectCriteria);
    List<ReservationDTO> selectReservationBoardList(@Param("memberNo") int memberNo, @Param("startRow") int startRow, @Param("endRow") int endRow);


}
