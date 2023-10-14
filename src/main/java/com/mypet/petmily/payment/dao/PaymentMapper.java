package com.mypet.petmily.payment.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import com.mypet.petmily.payment.Pagenation.SelectCriteria;
import java.util.List;

@Mapper
public interface PaymentMapper {
    /* 예약 조회 */
    List<ReservationDTO> selectReservationHistoryList(MemberDTO loginMember);



    /* 예약 조회 페이징 */
    int selectReservationTotalCount();
//    List<ReservationDTO> selectReservationBoardList(SelectCriteria selectCriteria);
}
