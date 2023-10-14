package com.mypet.petmily.review.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    /* 후기 작성 페이지 */
    ReservationHistoryDTO viewReservationInfo(Map<String, Object> loginMember);

    /* 후기 등록 */
    void registReview(ReviewDTO review);

    /* 후기 전체 리스트 조회 */
    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria, MemberDTO loginMember);

    int selectTotalCount(MemberDTO loginMember);

}
