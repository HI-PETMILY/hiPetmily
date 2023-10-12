package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.petSitter.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;


@Mapper
public interface PetSitterMapper {


    int registReservation(ReservationDTO reservation);
    PetSitterDTO selectAllInfo(PetSitterDTO petMember);
    List<CareerDTO> selectAllCareer(PetSitterDTO petMember);
    List<PetTagDTO> selectAllTag(PetSitterDTO petMember);
    List<SitterScheduleDTO> petSitterSchedule(PetSitterDTO petMember);
    PetJsonMemberDTO selectMemberInfo(PetSitterDTO petMember);
    PetJsonMemberDTO petSitterAddress(PetSitterDTO petMember);
    List<PetSitterDTO> selectPetSitterList();
}
