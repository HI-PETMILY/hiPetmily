package com.mypet.petmily.payment.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaymentMapper {
    List<ReservationDTO> selectReservationHistoryList(MemberDTO loginMember);

    List<ProgressReserveDTO> selectProgressReserveList(MemberDTO loginMember);
}
