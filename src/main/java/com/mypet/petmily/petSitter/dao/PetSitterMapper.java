package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PetSitterMapper {


    List<PetSitterDTO> selectPetSitterList();
}
