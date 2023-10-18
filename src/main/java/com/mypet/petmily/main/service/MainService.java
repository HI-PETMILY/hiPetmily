package com.mypet.petmily.main.service;

import com.mypet.petmily.main.dao.MainMapper;
import com.mypet.petmily.petSitterNew.dto.SitterReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MainService {

    private final MainMapper mainMapper;

    public MainService(MainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    // mainpage review
    public List<SitterReviewDTO> selectAllReviews() {
        return mainMapper.selectAllReviews();
    }

}
