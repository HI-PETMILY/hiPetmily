package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PetSitterService {
    public void selectPetSitterList(Map<String, String> searchMap, int page) {

        /*전체 게시글 수 확인 (검색어가 있는 경우 )=> 페이징 처리를 위해*/

        int totalCount = PetSitterMapper.selectTotalCount(searchMap);

    }
}
