package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.petSitter.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetSitterMapper {


    int registReservation(ReservationDTO reservation);
    int searchResNo(ReservationDTO reservation);

}
