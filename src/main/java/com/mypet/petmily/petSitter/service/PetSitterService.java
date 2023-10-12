package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PetSitterService {

    private final PetSitterMapper petSitterMapper;

    public PetSitterService(PetSitterMapper petSitterMapper) {
        this.petSitterMapper = petSitterMapper;
    }

    public void registReservation(ReservationDTO reservation) {

        petSitterMapper.registReservation(reservation);
    }

    public PetSitterDTO selectAllInfo(PetSitterDTO petMember) {

        return petSitterMapper.selectAllInfo(petMember);
    }

    public List<CareerDTO> selectAllCareer(PetSitterDTO petMember) {
        return petSitterMapper.selectAllCareer(petMember);
    }

    public List<PetTagDTO> selectAllTag(PetSitterDTO petMember) {
        return petSitterMapper.selectAllTag(petMember);
    }

    public PetJsonMemberDTO selectMemberInfo(PetSitterDTO petMember) {

        return petSitterMapper.selectMemberInfo(petMember);
    }

    public List<SitterScheduleDTO> petSitterSchedule(PetSitterDTO petMember) {
        return petSitterMapper.petSitterSchedule(petMember);
    }

    public PetJsonMemberDTO petSitterAddress(PetSitterDTO petMember) {
        return petSitterMapper.petSitterAddress(petMember);
    }
}
