package com.mypet.petmily.petSitterNew.dao;

import com.mypet.petmily.petSitterNew.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewPetSitterMapper {

    int registReservation(ReservationDTO reservation);
    NewPetSitterDTO selectAllInfo(NewPetSitterDTO petMember);
    List<CareerDTO> selectAllCareer(NewPetSitterDTO petMember);
    List<PetTagDTO> selectAllTag(NewPetSitterDTO petMember);
    List<SitterScheduleDTO> petSitterSchedule(NewPetSitterDTO petMember);
    PetJsonMemberDTO selectMemberInfo(NewPetSitterDTO petMember);
    PetJsonMemberDTO petSitterAddress(NewPetSitterDTO petMember);
    void insertTag(PetTagDTO petTag);
    void insertCareer(CareerDTO career);
    void insertSchedule(SitterScheduleDTO sitterSchedule);
    int insetInfo(NewPetSitterDTO petSitter);

}
