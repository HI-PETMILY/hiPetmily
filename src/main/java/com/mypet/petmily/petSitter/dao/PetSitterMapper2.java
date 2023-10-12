package com.mypet.petmily.petSitter.dao;

import com.mypet.petmily.petSitter.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetSitterMapper2 {

    int registReservation(ReservationDTO reservation);
    PetSitterDTO2 selectAllInfo(PetSitterDTO2 petMember);
    List<CareerDTO> selectAllCareer(PetSitterDTO2 petMember);
    List<PetTagDTO> selectAllTag(PetSitterDTO2 petMember);
    List<SitterScheduleDTO> petSitterSchedule(PetSitterDTO2 petMember);
    PetJsonMemberDTO selectMemberInfo(PetSitterDTO2 petMember);
    PetJsonMemberDTO petSitterAddress(PetSitterDTO2 petMember);

}
