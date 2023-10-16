package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.Pagenation;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PetSitterService {

    private PetSitterMapper petSitterMapper;

    public PetSitterService(PetSitterMapper petSitterMapper){

        this.petSitterMapper = petSitterMapper;
    }

    public List<PetSitterDTO> selectPetSitterList() {

        return petSitterMapper.selectPetSitterList();
    }



    public Map<String, Object> selectPetSitterPaging(Map<String, String> searchMap, int page) {


        /*전체 게시글 수 확인 (검색어가 있는 경우 포함)=> 페이징 처리를 위해*/


        int totalCount = petSitterMapper.selectTotalCount(searchMap);

        log.info("petSitterList totalCount{}", totalCount);


        // 페이징 처리와 연관된 값을 계산하여 SelectCriteria 타입의 객체에 담는다.
//
        int limit = 7; //한 페에지에 보여줄 게시물 수
        int buttonAmount = 5;//한번에 보여질 페이징 버튼 수
        //보여질 페이지에 대한 계산을 할 메소드
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("petSitterList selectCriteria : {}", selectCriteria);

        /*요청 페이지와 검색 기준에 맞는 게시글을 조회*/
        List<PetSitterDTO> petSitterDTOList = petSitterMapper.selectPetSitterListPaging(selectCriteria);

        log.info("petSitterDTOList : {} , petSitterDTOList");

        Map<String,Object> petSitterListAndPaging = new HashMap<>();
        petSitterListAndPaging.put("paging", selectCriteria);
        petSitterListAndPaging.put("petSitterList", petSitterDTOList);
        return petSitterListAndPaging;
    }
}
