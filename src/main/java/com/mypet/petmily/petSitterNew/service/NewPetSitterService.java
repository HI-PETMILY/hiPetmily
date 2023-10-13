package com.mypet.petmily.petSitterNew.service;

import com.mypet.petmily.petSitterNew.dao.NewPetSitterMapper;
import com.mypet.petmily.petSitterNew.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class NewPetSitterService {

    private final NewPetSitterMapper newPetSitterMapper;

    public NewPetSitterService(NewPetSitterMapper newPetSitterMapper) {
        this.newPetSitterMapper = newPetSitterMapper;
    }

    public void registReservation(ReservationDTO reservation) {

        newPetSitterMapper.registReservation(reservation);
    }

    public NewPetSitterDTO selectAllInfo(NewPetSitterDTO petMember) {

        return newPetSitterMapper.selectAllInfo(petMember);
    }

    public List<CareerDTO> selectAllCareer(NewPetSitterDTO petMember) {
        return newPetSitterMapper.selectAllCareer(petMember);
    }

    public List<PetTagDTO> selectAllTag(NewPetSitterDTO petMember) {
        return newPetSitterMapper.selectAllTag(petMember);
    }

    public PetJsonMemberDTO selectMemberInfo(NewPetSitterDTO petMember) {

        return newPetSitterMapper.selectMemberInfo(petMember);
    }

    public List<SitterScheduleDTO> petSitterSchedule(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterSchedule(petMember);
    }

    public PetJsonMemberDTO petSitterAddress(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterAddress(petMember);
    }

    public void insertTag(PetTagDTO petTag) {
        newPetSitterMapper.insertTag(petTag);
    }

    public void insertCareer(CareerDTO career) {
        newPetSitterMapper.insertCareer(career);
    }

}
