package com.mypet.petmily.review.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    /* 후기 등록 */
    void registReview(ReviewDTO review);

    /* 후기 전체 리스트 조회 */
    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    int selectTotalCount(Map<String, String> searchMap);
    // xml 작성해야 함

}
