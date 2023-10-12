package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.petSitter.dao.PetSitterMapper2;
import com.mypet.petmily.petSitter.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PetSitterService2 {

    private final PetSitterMapper2 petSitterMapper;

    public PetSitterService2(PetSitterMapper2 petSitterMapper) {
        this.petSitterMapper = petSitterMapper;
    }

    public void registReservation(ReservationDTO reservation) {

        petSitterMapper.registReservation(reservation);
    }

    public PetSitterDTO2 selectAllInfo(PetSitterDTO2 petMember) {

        return petSitterMapper.selectAllInfo(petMember);
    }

    public List<CareerDTO> selectAllCareer(PetSitterDTO2 petMember) {
        return petSitterMapper.selectAllCareer(petMember);
    }

    public List<PetTagDTO> selectAllTag(PetSitterDTO2 petMember) {
        return petSitterMapper.selectAllTag(petMember);
    }

    public PetJsonMemberDTO selectMemberInfo(PetSitterDTO2 petMember) {

        return petSitterMapper.selectMemberInfo(petMember);
    }

    public List<SitterScheduleDTO> petSitterSchedule(PetSitterDTO2 petMember) {
        return petSitterMapper.petSitterSchedule(petMember);
    }

    public PetJsonMemberDTO petSitterAddress(PetSitterDTO2 petMember) {
        return petSitterMapper.petSitterAddress(petMember);
    }

}
