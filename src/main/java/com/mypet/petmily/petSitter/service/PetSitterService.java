package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.board.dao.BoardMapper;
import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.Pagenation.Pagenation;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.SelectCriteria.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class PetSitterService {

    private final PetSitterMapper petSitterMapper;

    public PetSitterService(PetSitterMapper petSitterMapper){

        this.petSitterMapper = petSitterMapper;
    }

    public List<PetSitterDTO> selectPetSitterList() {

        return petSitterMapper.selectPetSitterList();
    }


//    public Map<String, Object> selectPetSitterList(Map<String, String> searchMap, int page) {
//
//        /*전체 게시글 수 확인 (검색어가 있는 경우 포함)=> 페이징 처리를 위해*/
//
//        int totalCount = PetSitterMapper.selectTotalCount(searchMap);
//
//        log.info("petSitterList totalCount{}", totalCount);
//
//
//        //2. 페이징 처리와 연관된 값을 계산하여 SelectCriteria 타입의 객체에 담는다.
//
//        int limit = 10; //한 페에지에 보여줄 게시물 수
//        int buttonAmount = 5;//한번에 보여질 페이징 버튼 수
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
//
//        return  null;
//    }
    
    
}
