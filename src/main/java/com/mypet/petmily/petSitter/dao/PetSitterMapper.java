package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

import com.mypet.petmily.petSitter.dto.PetSitterDTO;


@Mapper
public interface PetSitterMapper {

    List<PetSitterDTO> selectPetSitterList();

    int selectTotalCount(Map<String, String> searchMap);

    List<PetSitterDTO> selectPetSitterListPaging(SelectCriteria selectCriteria);


    PetSitterDTO selectMypage(MemberDTO loginMember);

    PetSitterDTO getAccount(MemberDTO loginMember);
}
