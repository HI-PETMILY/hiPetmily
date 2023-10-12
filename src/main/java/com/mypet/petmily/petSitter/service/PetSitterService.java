package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.member.dto.MemberDTO;
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

    public void registReservation(ReservationDTO reservation) throws PetSitterRegistException {

        int result = petSitterMapper.registReservation(reservation);

        if (!(result > 0)) {
            throw new PetSitterRegistException("펫시터 예약 등록을 실패하였습니다.");
        }
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
