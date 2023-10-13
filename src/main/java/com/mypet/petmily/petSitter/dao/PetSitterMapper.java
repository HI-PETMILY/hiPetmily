package com.mypet.petmily.petSitter.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;


@Mapper
public interface PetSitterMapper {

    List<PetSitterDTO> selectPetSitterList();
}
