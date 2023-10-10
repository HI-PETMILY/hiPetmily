package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetSitterMapper {


    int registReservation(ReservationDTO reservation);
    PetSitterDTO selectAllInfo(PetSitterDTO petMember);

}
