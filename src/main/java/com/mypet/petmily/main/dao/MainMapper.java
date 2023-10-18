package com.mypet.petmily.main.dao;

import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

    // mainpage review
    List<ReviewDTO> selectAllReviews();
}
