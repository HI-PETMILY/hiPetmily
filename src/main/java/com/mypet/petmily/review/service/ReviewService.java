package com.mypet.petmily.review.service;

import com.mypet.petmily.common.paging.Pagination;
import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import com.mypet.petmily.review.dao.ReviewMapper;
import com.mypet.petmily.review.dto.ReservationInfoDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper){
        this.reviewMapper = reviewMapper;
    }

    /* 후기 작성 페이지 */
    public ReservationHistoryDTO viewReservationInfo(Map<String, Object> param) {
        return reviewMapper.viewReservationInfo(param);
    }

    /* 후기 작성 */
    public void registReview(ReviewDTO review) {

        review.setRevCreatedDate(new Date());
        review.setRevModifyDate(new Date());
        review.setRevStatus("Y");

        reviewMapper.registReview(review);
    }

    /* 작성한 후기 전체 조회 */
    public Map<String, Object> selectReviewList(int page, MemberDTO loginMember) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = reviewMapper.selectTotalCount(loginMember);
        log.info("boardList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 5;         // 한 페이지에 보여줄 게시물(컨텐츠)의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, limit, buttonAmount);
        log.info("boardList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ReviewDTO> reviewList = reviewMapper.selectReviewList(selectCriteria, loginMember);
        log.info("reviewList : {}", reviewList);

        Map<String, Object> reviewListAndPaging = new HashMap<>();
        reviewListAndPaging.put("paging", selectCriteria);
        reviewListAndPaging.put("reviewList", reviewList);

        return reviewListAndPaging;
    }

    /*public ReservationHistoryDTO selectReserveInfo(MemberDTO loginMember) {
        return reviewMapper.reserveInfo(loginMember);
    }*/
}
